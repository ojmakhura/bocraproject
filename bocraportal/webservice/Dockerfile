# Dockerfile
FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bocraportal-api.jar
ENTRYPOINT ["java","-jar","/bocraportal-api.jar"]
