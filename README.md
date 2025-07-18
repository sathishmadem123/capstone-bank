# Capstone Bank – Basic Admin & Customer Management

This is a basic Spring Boot web application designed for simple **admin login** and **customer data management**. It was developed as part of a Capstone project and includes form-based input validation and user feedback.

## 📌 Features

- Admin login page with success/failure feedback
- Customer data entry form
- Validation for PAN number format
- Prevention of duplicate customer entries
- Delete customer by PAN
- Simple form-based frontend (HTML)

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Maven
- HTML (Thymeleaf or plain JSP/HTML)
- H2 in-memory database (likely, based on default Spring Boot behavior)

## 🧱 Project Structure

```text
src/
 └── main/
     ├── java/
     │   └── com.capstone
     │       ├── CapstoneBankApplication.java
     │       ├── WebConfigurationClass.java
     │       ├── WebEndpointController.java
     │       ├── admin/
     │       │   ├── Admin.java
     │       │   ├── AdminRepository.java
     │       │   └── AdminService.java
     │       └── customer/
     │           ├── Customer.java
     │           ├── CustomerRepository.java
     │           └── CustomerService.java
     └── resources/
         ├── templates/ (if used)
         ├── static/
         └── application.properties
```

## 🚀 Running the Application

### 1. Clone the repository

```bash
git clone https://github.com/sathishmadem123/capstone-bank.git
cd capstone-bank
```

### 2. Build the project

```bash
mvn clean install
```

### 3. Run the application

```bash
mvn spring-boot:run
```

### 4. Access in Browser

Visit:

```
http://localhost:8080
```

## 📸 Screenshots

The `Output/` folder contains screenshots of various UI components like:

- Admin Login (success/failure)
- Customer form
- Validation errors
- Duplicate entry alerts

---

## 🙋 Author

**Sathish**\
Capstone Project | Java Developer

---

> This is a simple educational project. Feel free to fork and extend it with more features like transactions, role-based security, and REST APIs.

