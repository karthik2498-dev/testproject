# Stage 1: Build using Gradle image
FROM gradle:8.4-jdk17 AS builder

WORKDIR /app

# Copy the whole project
#COPY --chown=gradle:gradle . . --commenting for testing app

# Copy Gradle files to the container
COPY CompletePro/build.gradle CompletePro/settings.gradle ./

# Copy the source code
COPY CompletePro/src ./src

# Build the application
RUN gradle build -x test --no-daemon

# Stage 2: Run using a minimal JRE image
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the jar from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
