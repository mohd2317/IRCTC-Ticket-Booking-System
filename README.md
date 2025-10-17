# 🚆 IRCTC Ticket Booking System
A **Java console-based application** that simulates a real-world **IRCTC Ticket Booking System**.  
It allows users to register, log in securely using BCrypt, search trains, and book tickets — all through an interactive console interface.  

This project demonstrates strong fundamentals in **Object-Oriented Programming (OOP)** principles, **JSON data storage**, and **modular Java programming**.


---

## 🧩 Features

✅ **User Registration & Login** (secure password hashing using BCrypt)  
✅ **Search Trains** by source and destination  
✅ **Book Tickets** with real-time seat allocation  
✅ **View Booked Tickets** for each user  
✅ **Cancel Tickets** and update seat availability  
✅ **JSON Data Storage** (acts as a mini local database)  
✅ **Clean Modular Code** (entities, service, util packages)  

---

## 🧱 Project Structure

```bash
IRCTC-Ticket-Booking-System/
│
├── pom.xml                      # Maven configuration (dependencies, build setup)
├── README.md                    # Project documentation
├── .gitignore                   # Ignore build files, IDE configs, logs, etc.
│
└── src/
    └── main/
        ├── java/
        │   └── ticket/
        │       └── booking/
        │           ├── Main.java                  # Entry point (main menu, app start)
        │           │
        │           ├── entities/                  # Data models (POJOs)
        │           │   ├── User.java              # Handles user info & booked tickets
        │           │   ├── Train.java             # Represents train details
        │           │   └── Ticket.java            # Represents a booked ticket
        │           │
        │           ├── service/                   # Business logic layer
        │           │   ├── TrainService.java      # Train search, availability, seat booking
        │           │   └── UserBookingService.java# User registration, login, and ticket handling
        │           │
        │           └── util/                      # Utility/helper classes
        │               └── UserServiceUtil.java   # Password hashing (BCrypt), common helpers
        │
        └── resources/
            └── localDB/                           # Local JSON storage (mock database)
                ├── trains.json                    # Stores all train data
                └── users.json                     # Stores all registered users


---

🧠 Folder-by-Folder Explanation

1️⃣ Root Folder
| File       | Description  |
|------------|--------------|
| pom.xml    | Contains project metadata and dependencies (Jackson, BCrypt, Maven compiler, etc.). |
| README.md  | Project documentation for GitHub. |
| .gitignore | Prevents unnecessary files (like `/target/`, IDE configs, `.class` files) from being committed. |

---

2️⃣ `src/main/java/ticket/booking/`
Your main Java source code — the heart of the project.

🔹 Main.java :
- Entry point of the application.  
- Displays the main menu for:
  - User Login / Registration  
  - Train Search  
  - Ticket Booking and Cancellation  

🔹 entities/
Contains data model classes (POJOs) that represent real-world entities.

| File        | Description  |
|-------------|--------------|
| User.java   | Stores user details, hashed password, and booked tickets list. |
| Ticket.java | Represents a single booked ticket with ID, train info, date, and user details. |
| Train.java  | Represents train data: name, number, routes, available seats, and timings. |

🔹 service/
Contains business logic that connects entities and utilities.

| File                    | Description  |
|-------------------------|--------------|
| TrainService.java       | Handles train search, seat availability, and updates JSON. |
| UserBookingService.java | Manages registration, login, ticket booking, and ticket cancellation. |

🔹 util/
Contains helper classes and static utilities.

| File                 | Description  |
|----------------------|--------------|
| UserServiceUtil.java | Uses BCrypt to hash and verify passwords; includes validation helpers. |

---

3️⃣ `src/main/resources/localDB/`
Simulates a small **local database** using JSON files.

| File        | Description  |
|-------------|--------------|
| users.json  | Stores all registered users with their hashed passwords. |
| trains.json | Stores train information, routes, seats, and timings. |


---

## ⚙️ Technologies Used

| Category  | Technology |
|-----------|-------------|
| Programming Language | Java 17 |
| Build Tool   | Maven |
| Data Storage | JSON (via Jackson) |
| Security     | BCrypt (password hashing) |
| IDE Recommended | IntelliJ IDEA / VS Code |
| Design Pattern  | MVC-style modular structure |

---

## 🧠 Key Learning Concepts

- Object-Oriented Programming (Encapsulation, Inheritance, Polymorphism)
- JSON Parsing and Data Binding using Jackson
- Password encryption and validation using BCrypt
- File handling and data persistence
- Console-based menu-driven interaction
- Exception handling and validation

---


## 💡 How to Use This

1. Copy the above Markdown into your `README.md` file.  
2. Replace `https://github.com/your-username` with your **actual GitHub username**.  
3. Once you push the repo, your GitHub page will look professional and visually structured with emojis, tables, and code blocks.

---
