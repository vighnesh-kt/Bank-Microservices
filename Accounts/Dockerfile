# Use OpenJDK 17
#start with a base image containing java runtime
FROM openjdk:17-jdk-slim

#MAINTAINER instruction is deprecated in favor of using label
# MAINTAINER eazybytes.com
#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="v"

# Set working directory inside the container
WORKDIR /bank-microservices/accounts

# Copy the JAR file into the working directory
COPY target/Accounts.jar Accounts.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "Accounts.jar"]
