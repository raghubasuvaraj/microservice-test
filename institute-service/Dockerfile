# Start with a base image containing Java runtime
FROM adoptopenjdk/openjdk11

# Add Maintainer Info
LABEL maintainer="lakshman@socialtalentagency.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8003 available to the world outside this container
EXPOSE 8003

# The application's jar file
ARG JAR_FILE=target/institute-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} institute.jar

# Run the jar file 
ENTRYPOINT ["java","-Dspring.profiles.active=${ENV}","-jar","/institute.jar"]
