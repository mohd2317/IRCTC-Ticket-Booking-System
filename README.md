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
IRCTC-Ticket-Booking-System/
│
├── pom.xml                                # Maven configuration (dependencies, build setup)
├── README.md                              # Project documentation
├── .gitignore                             # Ignore build files, IDE configs, logs, etc.
│
└── src/
    └── main/
        ├── java/
        │   └── ticket/
        │       └── booking/
        │           ├── Main.java                      # Entry point (main menu, app start)
        │           │
        │           ├── entities/                      # Data models (POJOs)
        │           │   ├── User.java                  # Handles user info & booked tickets
        │           │   ├── Train.java                 # Represents train details
        │           │   └── Ticket.java                # Represents a booked ticket
        │           │
        │           ├── service/                       # Business logic layer
        │           │   ├── TrainService.java          # Train search, availability, seat booking
        │           │   └── UserBookingService.java    # User registration, login, and ticket handling
        │           │
        │           └── util/                          # Utility/helper classes
        │               └── UserServiceUtil.java       # Password hashing (BCrypt), common helpers
        │
        └── resources/
            └── localDB/                               # Local JSON storage (mock database)
                ├── trains.json                        # Stores all train data
                └── users.json                         # Stores all registered users



🧠 Folder-by-Folder Explanation

1️⃣ Root Folder

pom.xml — Contains project metadata and dependencies (Jackson, BCrypt, Maven compiler, etc.)
README.md — Project documentation for GitHub.
.gitignore — Prevents unnecessary files (like /target/, IDE configs, .class files) from being committed.


2️⃣ src/main/java/ticket/booking/

Your Java source code — the heart of the project.

🔹 Main.java
Runs the application.
Contains the main menu for:
Login / Registration
Train search
Ticket booking and cancellation

🔹 entities/
Contains data model classes (POJOs) that represent the real-world entities.
File	Description
User.java	Stores user details, hashed password, and booked tickets list.
Ticket.java	Represents a single booked ticket with ID, train info, date, and user details.
Train.java	Represents train data: name, number, routes, available seats, and timings.

🔹 service/
Contains business logic that connects entities and utilities.
File	Description
TrainService.java	Handles train search, seat availability, and updates JSON.
UserBookingService.java	Manages registration, login, ticket booking, and ticket cancellation.

🔹 util/
Contains helper classes and static utilities.
File	Description
UserServiceUtil.java	Uses BCrypt to hash and verify passwords; includes validation helpers.


3️⃣ src/main/resources/localDB/

Your data layer, simulating a small local database using JSON.
File	Description
users.json	Stores all registered users with their hashed passwords.
trains.json	Stores train information, routes, seats, and times.

---

## ⚙️ Technologies Used

| Category | Technology |
|-----------|-------------|
| Programming Language | Java 17 |
| Build Tool | Maven |
| Data Storage | JSON (via Jackson) |
| Security | BCrypt (password hashing) |
| IDE Recommended | IntelliJ IDEA / VS Code |
| Design Pattern | MVC-style modular structure |

---

## 🧠 Key Learning Concepts

- Object-Oriented Programming (Encapsulation, Inheritance, Polymorphism)
- JSON Parsing and Data Binding using Jackson
- Password encryption and validation using BCrypt
- File handling and data persistence
- Console-based menu-driven interaction
- Exception handling and validation

---


This project is licensed under the MIT License – feel free to use and modify it for learning purposes.

💬 Comments for GitHub Commit Messages (Recommended)

Here are some commit message examples you can use while uploading the project:
Commit Type	Example Message
Initial Commit	🎉 Initial commit - Added base project structure
Feature	✨ Added User entity and ticket booking logic
Fix	🐞 Fixed null pointer issue in UserServiceUtil
Docs	📝 Updated README and project description
Refactor	♻️ Refactored code for better readability and performance
Data	📁 Added sample trains.json and users.json
Build	🏗️ Updated Maven dependencies and compiler version
🏁 Summary

This project is a console-based simulation of an IRCTC Ticket Booking System,
built purely in Java using OOP principles, JSON persistence, and secure password handling.

---

## 💡 How to Use This

1. Copy the above Markdown into your `README.md` file.  
2. Replace `https://github.com/your-username` with your **actual GitHub username**.  
3. Once you push the repo, your GitHub page will look professional and visually structured with emojis, tables, and code blocks.

---
