# Start with a base image containing Java runtime
#FROM openjdk:8-jdk-alpine
FROM adoptopenjdk/openjdk11

# Add Maintainer Info
LABEL maintainer="lakshman.m@socialtalentagency.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8888 available to the world outside this container
EXPOSE 8888

# The application's jar file
ARG JAR_FILE=target/config-server-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} config-server.jar


# Run the jar file 
ENTRYPOINT ["java","-Dspring.profiles.active=native,local","-jar","config-server.jar"]