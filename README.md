# Online Exam Proctoring System

## Project Overview
The Online Exam Proctoring System is a Java-based application designed to conduct online exams with basic proctoring features.  
It supports role-based access for **Admin** and **Student**, exam attempt tracking, activity monitoring, and result management.

The application is built using **Java Swing for the user interface**, **JDBC for database connectivity**, and **MySQL** as the backend database.  
The system implements full **CRUD operations**.

---

## Technologies Used
- Java (Core Java & Swing)
- JDBC (Java Database Connectivity)
- MySQL
- Eclipse IDE
- Git & GitHub

---

## Why Java Swing?
Java Swing was chosen to build a standalone desktop application that:
- Does not require a web server (Tomcat)
- Integrates easily with Core Java and JDBC
- Enables faster development for desktop-based systems
- Is suitable for academic and enterprise-level internal tools

This design ensures a clear separation between the UI layer, business logic, and database layer.

---

## Features

### Admin Module
- Admin login
- Add exam questions
- View student results
- Monitor exam attempts

### Student Module
- Student login
- Start exam
- Submit exam
- View exam results

### Proctoring Features
- Exam attempt tracking
- Suspicious activity logging (tab switching, window minimization, etc.)

---

## Database Design
The project uses MySQL with the following tables:
- users
- exams
- questions
- exam_attempts
- activity_logs

---

## Project Demo
Below is a short demonstration video showcasing the working of the Online Exam Proctoring System.

https://github.com/user-attachments/assets/069c509b-806c-4632-aee5-373fac20b14a


