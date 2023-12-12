# Use an official OpenJDK runtime as a parent image
FROM openjdk:15

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/todo-service-1.0-SNAPSHOT.jar app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "app.jar"]
