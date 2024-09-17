# University Management System

## Overview

This project is a **University Management System** that manages users (students, professors, and administrators), courses, and complaints within a university setting. The system allows users to register, log in, perform actions based on their roles, and manage academic courses.

### Key Features:
- **User Roles**: Supports different user roles like Students, Professors, and Administrators.
- **User Registration**: Students and Professors can register with the system.
- **Course Management**: Professors can manage courses, while students can register for or drop courses.
- **Complaint Management**: Students can submit complaints, and Administrators can handle these complaints.
- **Login System**: A secure login system based on user roles.

---

## Table of Contents

- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Core Functionalities](#core-functionalities)
    - [Users](#users)
    - [Courses](#courses)
    - [Complaints](#complaints)
    - [Login System](#login-system)
- [Interfaces and Abstraction](#interfaces-and-abstraction)
- [How to Use](#how-to-use)

---

## Technologies Used

- **Java**: Programming language used to build the project.
- **Java Packages**: To modularize the code based on different features like users, services, complaints, etc.
- **Interfaces and Abstraction**: For loosely coupling services and implementing role-based functionality.
- **Scanner**: Used for user input.

---

## Project Structure

---
```
src
│
├── main
│   ├── complaints
│   │   └── Complaint.java       
│   ├── courses
│   │   ├── Course.java         
|   |   └── CourseCatalog.java   
│   ├── data
|   |   ├── CourseData.java
│   │   └── UserData.java  
|   ├── interfaces   
|   |   ├── ComplaintInterface.java  
|   |   └── UserLoginService.java  
│   ├── services
│   │   ├── UserService.java     
│   │   ├── StudentLoginService.java  
│   │   ├── ProfessorLoginService.java  
│   │   ├── AdminLoginService.java   
|   |   └── AdminService.java 
│   ├── users
│   │   ├── User.java            
│   │   ├── Student.java         
│   │   ├── Professor.java       
│   │   └── Administrator.java   
│   Main.java
README.md                    
```
---

## Core Functionalities

### 1. Users

#### a. Student:
- **Register for Courses**: Students can view available courses and register for them.
- **Drop Courses**: Students can drop the courses they are enrolled in.
- **View Weekly Schedule**: Displays the courses registered by the student for the week.
- **View Grades**: Students can view their grades after completing courses.
- **Complaint System**: Students can submit complaints to the admin about any issues they encounter.

#### b. Professor:
- **View Assigned Courses**: Professors can see the courses they are assigned to.
- **Manage Courses**: Professors can manage their assigned courses (add/drop students, set grades, etc.).

#### c. Administrator:
- **Course Management**: Administrators can manage all courses, adding or removing them as needed.
- **Student Management**: Administrators can view, add, or remove students from the system.
- **Complaint Handling**: Administrators can view and resolve complaints submitted by students.

---

### 2. Courses
Courses are central to the academic operations of the system. Professors can manage their assigned courses, and students can register for or drop them. Administrators have full control over the course offerings.

### 3. Complaints
The system allows students to submit complaints that are stored in the system and can be resolved by administrators. Each complaint is associated with a student and can be managed by administrators.

### 4. Login System
The system features a secure login process where users must log in with their email and password. Based on their role (student, professor, administrator), they are directed to the appropriate functionalities.

---

## Interfaces and Abstraction

The project uses interfaces and abstraction to decouple logic and create a scalable design. The `UserLoginInterface` interface defines the `login()` method, ensuring that each role (student, professor, and admin) implements its own login logic:

- **StudentLoginService**: Implements the `login()` for student-specific tasks.
- **ProfessorLoginService**: Implements the `login()` for professor-specific tasks.
- **AdminLoginService**: Implements the `login()` for administrator-specific tasks.

This design allows for easy extensibility and maintenance.

---

## How to Use

1. **Create User**:
- Select whether to create a Student or Professor account.
- Enter the necessary details like name, email, and password.

2. **Login**:
- Students and Professors can log in and perform actions specific to their roles.
- Administrators can log in using the admin login and manage courses, students, and complaints.

3. **Manage Courses**:
- Professors can manage their assigned courses.
- Students can register for or drop courses.

4. **Handle Complaints**:
- Students can submit complaints.
- Administrators can view and resolve complaints.
