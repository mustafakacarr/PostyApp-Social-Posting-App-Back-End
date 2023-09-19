FROM openjdk:11
EXPOSE 8080
COPY postyApp-0.0.1-SNAPSHOT.jar postyApp.jar
ENTRYPOINT ["java", "-jar", "/postyApp.jar"]