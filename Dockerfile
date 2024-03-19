# Use a JDK 19 base image
FROM amazoncorretto:17-alpine-jdk

# Set the working directory in the Docker container
WORKDIR /app

# Copy all files from directory to image
COPY . .

# build the project avoiding tests
RUN ./gradlew clean build -x test

# Expose port 8080
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "./build/libs/spc-backend-0.0.1-SNAPSHOT.jar"]