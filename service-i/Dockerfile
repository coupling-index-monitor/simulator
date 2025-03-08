# Use an official Maven image to build the application
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the project files and build the application
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Use a lightweight JDK runtime for running the application
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built JAR file from the Maven build stage
COPY --from=build /app/target/service-i-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]