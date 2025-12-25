# MyBankApp

**MyBankApp** is a mini banking application built with Spring Boot. It allows you to manage users, their accounts, and bank cards via REST API.

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- (Planned) Redis
- (Planned) Spring Security

## Features

- Create, update, delete, and fetch **users**
- Create, update, delete, and fetch **accounts**
- Deposit and withdraw money from accounts
- Create, update, delete, and fetch **bank cards**
- Find cards by card number
- Handles relationships between accounts and cards

## API Endpoints

### Users

| Method | Endpoint         | Description              |
|--------|----------------|--------------------------|
| POST   | /users          | Create a new user        |
| GET    | /users          | Get all users            |
| GET    | /users/{id}     | Get user by ID           |
| PUT    | /users/{id}     | Update user              |
| DELETE | /users/{id}     | Delete user              |

### Accounts

| Method | Endpoint                        | Description                  |
|--------|--------------------------------|------------------------------|
| POST   | /users/{userId}/accounts        | Create a new account         |
| GET    | /users/{userId}/accounts        | Get all accounts for user    |
| GET    | /users/{userId}/accounts/{id}   | Get account by ID            |
| PUT    | /users/{userId}/accounts/{id}   | Update account               |
| DELETE | /users/{userId}/accounts/{id}   | Delete account               |
| POST   | /users/{userId}/accounts/{id}/deposit  | Deposit money              |
| POST   | /users/{userId}/accounts/{id}/withdraw | Withdraw money             |
| GET    | /users/{userId}/accounts/{id}/balance  | Show balance               |

### Cards

| Method | Endpoint                             | Description                  |
|--------|-------------------------------------|------------------------------|
| POST   | /accounts/{accountId}/cards          | Create a new card            |
| GET    | /accounts/{accountId}/cards          | Get all cards for account    |
| GET    | /accounts/{accountId}/cards/{id}    | Get card by ID               |
| PUT    | /accounts/{accountId}/cards/{id}    | Update card                  |
| DELETE | /accounts/{accountId}/cards/{id}    | Delete card                  |
| GET    | /accounts/{accountId}/cards/by-number/{cardNumber} | Find card by number |

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/soulshadeosiris/MyBankAppSpringBoot.git
    ```
2. Configure MySQL database in application.properties:
   spring.datasource.url=jdbc:mysql://localhost:3306/mybankapp
   spring.datasource.username=YOUR_DB_USERNAME
   spring.datasource.password=YOUR_DB_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true

3. Run the application:
   mvn spring-boot:run

4. Use Postman or any API client to test the endpoints.

## Future Improvements

- Add Redis caching for better performance
- Implement Spring Security for authentication & authorization
- Build a frontend to make it a full-stack application
