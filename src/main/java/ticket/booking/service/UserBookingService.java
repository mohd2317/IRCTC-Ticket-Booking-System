package ticket.booking.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class UserBookingService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<User> userList;
    private User user;
    private static final String USER_FILE_PATH = "src/main/resources/localDB/users.json";

    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUserListFromFile();
    }

    public UserBookingService() throws IOException {
        loadUserListFromFile();
    }

    private void loadUserListFromFile() throws IOException {
        File users = new File(USER_FILE_PATH);
        if (!users.exists()) {
            userList = new ArrayList<>();
            return;
        }
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
        if (userList == null) userList = new ArrayList<>();
        userList.forEach(u -> {
            if (u.getTicketsBooked() == null) u.setTicketsBooked(new ArrayList<>());
        });
    }

    private void saveUserListToFile() throws IOException {
        objectMapper.writeValue(new File(USER_FILE_PATH), userList);
    }

    public User loginUser(String loginInput, String password) {
        if (loginInput == null || password == null) return null;
        Optional<User> foundUser = userList.stream()
                .filter(u -> (u.getEmail() != null && u.getEmail().equalsIgnoreCase(loginInput)) ||
                        (u.getPhoneNumber() != null && u.getPhoneNumber().equals(loginInput)))
                .filter(u -> UserServiceUtil.checkPassword(password, u.getHashedPassword()))
                .findFirst();
        if (foundUser.isPresent()) {
            this.user = foundUser.get();
            System.out.println("Login successful. Welcome, " + user.getName() + "!");
            return this.user;
        } else {
            System.out.println("Invalid credentials.");
            return null;
        }
    }

    public Boolean signUp(User newUser) {
        try {
            if (newUser == null) return false;
            boolean exists = userList.stream().anyMatch(u ->
                    (u.getEmail() != null && u.getEmail().equalsIgnoreCase(newUser.getEmail())) ||
                            (u.getPhoneNumber() != null && u.getPhoneNumber().equals(newUser.getPhoneNumber()))
            );
            if (exists) return false;

            newUser.setUserId(UUID.randomUUID().toString());
            if (newUser.getTicketsBooked() == null) newUser.setTicketsBooked(new ArrayList<>());
            userList.add(newUser);
            saveUserListToFile();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Ticket bookTrainSeat(Train train, int row, int seat, String travelDate, User user) {
        try {
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addOrUpdateTrain(train);

                    Ticket ticket = new Ticket();
                    ticket.setTrainNo(train.getTrainNo());
                    ticket.setTrainName(train.getTrainName());
                    ticket.setSource(train.getStations().get(0));
                    ticket.setDestination(train.getStations().get(train.getStations().size() - 1));
                    ticket.setPassengerName(user.getName());
                    ticket.setTravelDate(travelDate);
                    ticket.setRow(row);
                    ticket.setSeat(seat);
                    ticket.setTrain(train);

                    user.getTicketsBooked().add(ticket);
                    updateUserInList(user);
                    saveUserListToFile();
                    return ticket;
                } else {
                    System.out.println("Seat already booked!");
                    return null;
                }
            } else {
                System.out.println("Invalid seat index!");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean cancelBooking(String ticketId) {
        if (ticketId == null || ticketId.isEmpty() || user == null || user.getTicketsBooked() == null) return false;
        boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(ticketId));
        if (removed) {
            updateUserInList(user);
            try { saveUserListToFile(); } catch (IOException e) { e.printStackTrace(); }
        }
        return removed;
    }

    private void updateUserInList(User updatedUser) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserId().equals(updatedUser.getUserId())) {
                userList.set(i, updatedUser);
                break;
            }
        }
    }
}
