# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-slim

# Install necessary dependencies and Tesseract
RUN apt-get update && \
    apt-get install -y --no-install-recommends tesseract-ocr && \
    apt-get install tesseract-ocr-ces && \
    rm -rf /var/lib/apt/lists/*

# Set the Tesseract data directory
ENV TESSDATA_PREFIX=/usr/share/tesseract-ocr/4.00/tessdata

# Set the working directory inside the container
WORKDIR /app

# Copy the project's files into the container
COPY . .

# Grant execution permissions to the mvnw script
RUN chmod +x ./mvnw

# Build the Java application
RUN ./mvnw clean package

# Expose the service's port (Replace 8080 with the actual port your service uses)
EXPOSE 8080

# Start the Java service
CMD ["java", "-jar", "target/CustomOCRService-0.0.1-SNAPSHOT.jar"]