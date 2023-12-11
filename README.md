# Todo Service
Spring Boot implementation of a simple Todo service

## Service Description

The Todo Service is a Spring Boot-based application that provides a simple API for managing todo items. It allows users to add new items, change descriptions, mark items as done or not done, and automatically updates the status of items that are past their due date.

### Assumptions

- Todo items have a status field that can be "not done," "done," or "past due."
- Items can be marked with a due date, and the service automatically updates the status to "past due" if the due date has passed.

## Tech Stack

- **Runtime Environment:** Java
- **Framework:** Spring Boot
- **Key Libraries:** Spring Web, Spring Data JPA
- **Database:** H2 Database (in-memory)

## How to Use

### Build the Service

To build the service, ensure you have [Maven](https://maven.apache.org/) installed. Then, run the following command:

```bash
mvn clean install

### Run Automatic Tests

The service includes automated tests. Run the tests using:

```bash
mvn test

### Run the Service Locally

To run the service locally, execute the following command:

```bash
java -jar target/todo-service-1.0-SNAPSHOT.jar

The service will be accessible at http://localhost:8080/swagger-ui.html.

### API Documentation

The API documentation is available at http://localhost:8080/swagger-ui.html. Use this documentation to understand the available endpoints and their functionalities.
### Additional Notes

The service automatically updates the status of items that are past their due date during a scheduled task.
Make sure to configure your IDE and development environment to support Java 15.

Feel free to reach out for any questions or issues!


