# Use Java 17 runtime
FROM eclipse-temurin:17-jdk-alpine

# App directory inside container
WORKDIR /app

# Copy jar file
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose Render port
EXPOSE 8080

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
