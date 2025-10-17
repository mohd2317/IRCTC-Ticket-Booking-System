
package ticket.booking.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private String name;
    private String email;
    private String phoneNumber;
    private transient String password; // not saved in JSON when serializing the object to disk or JSON (transient)
    private String hashedPassword;     // stored instead of plain password
    private List<Ticket> ticketsBooked;
    private String userId = UUID.randomUUID().toString();

    public User() {
        this.ticketsBooked = new ArrayList<>(); // Avoid null list
        //Initializes ticketsBooked to an empty ArrayList so the list is never null.
        // This avoids NullPointerException when code calls getTicketsBooked() and then manipulates the list.
    }

    public User(String name, String email, String phoneNumber, String password, String hashedPassword, List<Ticket> ticketsBooked, String userId) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.ticketsBooked = (ticketsBooked == null) ? new ArrayList<>() : ticketsBooked;
        //Defensive: if caller passes null, we replace it with an empty list. That keeps the internal state robust.
        this.userId = userId;
    }

    public void printTickets() { //printTickets() is a convenience method that prints all booked tickets to the console.
        if (ticketsBooked == null || ticketsBooked.isEmpty()) {
            System.out.println("No tickets booked yet.");
            return;
        }
        ticketsBooked.forEach(ticket -> System.out.println(ticket.getTicketInfo()));
        //If there are tickets, it iterates with forEach and prints each ticket’s info using ticket.getTicketInfo()
        // (Ticket class provides that).
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.ticketsBooked = (ticketsBooked == null) ? new ArrayList<>() : ticketsBooked;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
