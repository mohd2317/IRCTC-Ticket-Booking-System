package ticket.booking.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainService {
    private List<Train> trainList;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String TRAIN_DB_PATH = "src/main/resources/localDB/trains.json";

    public TrainService() throws IOException {
        File trainsFile = new File(TRAIN_DB_PATH);
        if (!trainsFile.exists()) {
            trainList = new ArrayList<>();
            return;
        }
        try {
            trainList = objectMapper.readValue(trainsFile, new TypeReference<List<Train>>() {});
            if (trainList == null) trainList = new ArrayList<>();
            // Initialize seats if missing
            for (Train t : trainList) {
                if (t.getSeats() == null || t.getSeats().isEmpty()) {
                    List<List<Integer>> defaultSeats = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        defaultSeats.add(new ArrayList<>(Collections.nCopies(6, 0)));
                    }
                    t.setSeats(defaultSeats);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading train DB, starting with empty list: " + e.getMessage());
            trainList = new ArrayList<>();
        }
    }

    public List<Train> searchTrains(String source, String destination) {
        if (source == null || destination == null) return Collections.emptyList();
        String src = source.trim().toLowerCase();
        String dest = destination.trim().toLowerCase();

        return trainList.stream()
                .filter(train -> validTrain(train, src, dest))
                .collect(Collectors.toList());
    }

    public void addOrUpdateTrain(Train train) {
        OptionalInt index = IntStream.range(0, trainList.size())
                .filter(i -> trainList.get(i).getTrainNo().equalsIgnoreCase(train.getTrainNo()))
                .findFirst();
        if (index.isPresent()) {
            trainList.set(index.getAsInt(), train);
        } else {
            trainList.add(train);
        }
        saveTrainListToFile();
    }

    private void saveTrainListToFile() {
        try {
            objectMapper.writeValue(new File(TRAIN_DB_PATH), trainList);
        } catch (IOException e) {
            System.err.println("Error saving train list: " + e.getMessage());
        }
    }

    private boolean validTrain(Train train, String source, String destination) {
        List<String> stations = train.getStations();
        if (stations == null || stations.isEmpty()) return false;
        List<String> lowerStations = stations.stream()
                .filter(Objects::nonNull)
                .map(String::toLowerCase)
                .toList();
        int srcIndex = lowerStations.indexOf(source);
        int destIndex = lowerStations.indexOf(destination);
        return srcIndex != -1 && destIndex != -1 && srcIndex < destIndex;
    }
}
