FROM openjdk:17-alpine
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} challenge-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "challenge-0.0.1-SNAPSHOT.jar"]