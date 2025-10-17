package ticket.booking.entities;

import java.util.UUID;

public class Ticket {
    private String ticketId;
    private String trainNo;
    private String trainName;
    private String source;
    private String destination;
    private String travelDate;
    private String passengerName;
    private int row;
    private int seat;
    private Train train;

    public Ticket() {
        this.ticketId = UUID.randomUUID().toString();
    }

    // Getters & Setters
    public String getTicketId() {
        return ticketId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getTicketInfo() {
        return String.format("""
            -------------------------------
            🎟️  Ticket ID: %s
            🚆 Train: %s (%s)
            🛤️ Route: %s ➜ %s
            👤 Passenger: %s
            💺 Seat: Row %d, Seat %d
            📅 Travel Date: %s
            -------------------------------
            """, ticketId, trainName, trainNo, source, destination, passengerName, row, seat, travelDate);
    }
}
