# Dockerfile
FROM openjdk:11
ARG JAR_FILE=bocraportal/webservice/target/*.jar
COPY ${JAR_FILE} bocraportal-webservice.jar
ENTRYPOINT ["java","-jar","/bocraportal-webservice.jar"]
