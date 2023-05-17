# Use Oracle OpenJDK 17.0.2 as base image
FROM oracle/openjdk:17.0.2

# Setting Maintainer Name
LABEL maintainer="Rupesh Bagde"

# Setting the Work Directory
WORKDIR /Customer-Data-Management

# Copy the Micronaut application JAR file to the container
COPY build/libs/Customer-Data-Management-*.jar Customer-Data-Management.jar

# Expose the Micronaut port (5424)
EXPOSE 5424

# Start the application
ENTRYPOINT ["java", "-jar", "Customer-Data-Management.jar"]