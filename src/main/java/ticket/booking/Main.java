package ticket.booking;

import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.service.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static User currentUser;
    private static UserBookingService userService;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

    public static void main(String[] args) {
        try {
            userService = new UserBookingService();
            System.out.println("=== Welcome to IRCTC Console Booking System ===");

            boolean running = true;
            while (running) {
                System.out.println("\n1. Login");
                System.out.println("2. Sign Up");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = readInt();

                switch (choice) {
                    case 1 -> loginFlow();
                    case 2 -> signUpFlow();
                    case 3 -> {
                        running = false;
                        System.out.println("Thank you for using IRCTC!");
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }

        } catch (IOException e) {
            System.err.println("Error initializing system: " + e.getMessage());
        }
    }

    // ---------------- LOGIN ----------------
    private static void loginFlow() {
        System.out.print("Enter email or phone: ");
        String loginInput = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User loggedIn = userService.loginUser(loginInput, password);
        if (loggedIn != null) {
            currentUser = loggedIn;
            userMenu();
        }
    }

    // ---------------- SIGN UP ----------------
    private static void signUpFlow() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPhoneNumber(phone);
        newUser.setHashedPassword(UserServiceUtil.hashPassword(password));

        boolean result = userService.signUp(newUser);
        if (result) System.out.println("✅ User registered successfully!");
        else System.out.println("❌ Registration failed. Email or phone might already exist.");
    }

    // ---------------- USER MENU ----------------
    private static void userMenu() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Search Trains");
            System.out.println("2. View My Bookings");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> searchAndBookTrain();
                case 2 -> viewBookings();
                case 3 -> cancelBooking();
                case 4 -> {
                    loggedIn = false;
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    // ---------------- SEARCH & BOOK ----------------
    private static void searchAndBookTrain() {
        try {
            System.out.print("Enter source station: ");
            String source = scanner.nextLine().trim();
            System.out.print("Enter destination station: ");
            String destination = scanner.nextLine().trim();

            List<Train> trains = userService.getTrains(source, destination);
            if (trains.isEmpty()) {
                System.out.println("No trains found for this route.");
                return;
            }

            System.out.println("\nAvailable Trains:");
            for (int i = 0; i < trains.size(); i++) {
                System.out.println((i + 1) + ". " + trains.get(i).getTrainInfo());
            }

            System.out.print("Select a train number to book: ");
            int trainChoice = readInt();
            if (trainChoice < 1 || trainChoice > trains.size()) {
                System.out.println("Invalid choice.");
                return;
            }

            Train selectedTrain = trains.get(trainChoice - 1);
            showSeatMatrix(selectedTrain);

            System.out.print("Enter row number (0-based): ");
            int row = readInt();
            System.out.print("Enter seat number (0-based): ");
            int seat = readInt();

            System.out.print("Enter travel date (dd-MM-yyyy hh:mm AM/PM): ");
            String travelInput = scanner.nextLine().trim();

            // validate travel date
            try {
                LocalDateTime.parse(travelInput, DATE_FORMAT);
            } catch (Exception e) {
                System.out.println("Invalid date format, using current time instead.");
                travelInput = LocalDateTime.now().format(DATE_FORMAT);
            }

            Ticket ticket = userService.bookTrainSeat(selectedTrain, row, seat, travelInput, currentUser);
            if (ticket != null) {
                System.out.println("✅ Seat booked successfully!");
                System.out.println(ticket.getTicketInfo());
            } else {
                System.out.println("❌ Booking failed. Seat may already be booked.");
            }

        } catch (Exception e) {
            System.err.println("Booking error: " + e.getMessage());
        }
    }

    // ---------------- VIEW BOOKINGS ----------------
    private static void viewBookings() {
        if (currentUser.getTicketsBooked() == null || currentUser.getTicketsBooked().isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }
        System.out.println("\nYour Bookings:");
        currentUser.printTickets();
    }

    // ---------------- CANCEL BOOKING ----------------
    private static void cancelBooking() {
        System.out.print("Enter ticket ID to cancel: ");
        String ticketId = scanner.nextLine().trim();

        boolean result = userService.cancelBooking(ticketId);
        if (result) System.out.println("✅ Booking canceled successfully!");
        else System.out.println("❌ Ticket not found.");
    }

    // ---------------- DISPLAY SEATS ----------------
    private static void showSeatMatrix(Train train) {
        System.out.println("\nSeat Layout (0 = available, 1 = booked):");
        List<List<Integer>> seats = train.getSeats();
        for (int i = 0; i < seats.size(); i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 0; j < seats.get(i).size(); j++) {
                System.out.print(seats.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    // ---------------- UTILITY ----------------
    private static int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}
