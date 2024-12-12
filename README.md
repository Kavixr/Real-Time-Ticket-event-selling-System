# RealTicketingSystem

## Overview
The RealTicketingSystem is a simulation of a ticket selling and retrieval process with multiple vendors and customers. The system allows users to simulate the process of ticket distribution by vendors and retrieval by customers, utilizing multithreading to handle concurrent operations.

## Technologies Used
- **Spring Boot**: Backend framework for REST API development
- **Java**: Programming language for implementing the business logic
- **JPA (Jakarta Persistence API)**: Database interactions and entity mapping
- **H2 Database**: In-memory database for storing simulation data
- **Multithreading**: Simulating vendors and customers concurrently

## Features
- **Simulation Control**: Start and stop the ticket simulation.
- **Multithreaded Simulation**: Simulate vendors adding tickets to the pool and customers retrieving them.
- **Database Integration**: The simulation settings (e.g., total tickets, release rate, capacity, etc.) are saved in the database for each run.
- **Vendor and Customer Threads**: Each vendor and customer runs as a separate thread to simulate real-time operations.
- **Concurrency**: Ticket pool has a synchronized add and remove mechanism to handle the concurrent access by vendors and customers.

## Project Structure

- **`TicketController.java`**: Controller class responsible for handling API requests to start and stop the simulation.
- **`TicketService.java`**: Service class responsible for managing ticket pool, vendor/customer threads, and simulation logic.
- **`TicketPool.java`**: A model representing a shared pool of tickets with synchronized add and remove methods.
- **`Vendor.java`**: A model representing a vendor that adds tickets to the pool at a specified rate.
- **`Customer.java`**: A model representing a customer that retrieves tickets from the pool at a specified rate.
- **`DBTicket.java`**: A JPA entity for storing simulation parameters like total tickets, release rate, etc.
- **`TicketRepo.java`**: JPA repository for interacting with the `DBTicket` entity.

## Setup Instructions

### Prerequisites
- JDK 17 or above
- Maven
- Spring Boot (should be bundled in the project)

### Steps to Run the Application

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/yourusername/RealTicketingSystem.git
    ```

2. **Navigate to Project Directory**:
    ```bash
    cd RealTicketingSystem
    ```

3. **Build the Project**:
    ```bash
    mvn clean install
    ```

4. **Run the Application**:
    ```bash
    mvn spring-boot:run
    ```

5. **API Endpoints**:
    - `POST /api/ticket/start`: Start the simulation. The request body should contain the following data:
      ```json
      {
          "totalTickets": 100,
          "ticketReleaseRate": 2000,
          "customerRetrievalRate": 3000,
          "maxTicketCapacity": 50,
          "numVendors": 3,
          "numCustomers": 10
      }
      ```
    - `POST /api/ticket/stop`: Stop the simulation.

## Example API Requests

### Start Simulation

**Request:**
```bash
POST http://localhost:8080/api/ticket/start
Content-Type: application/json

{
    "totalTickets": 100,
    "ticketReleaseRate": 2000,
    "customerRetrievalRate": 3000,
    "maxTicketCapacity": 50,
    "numVendors": 3,
    "numCustomers": 10
}
