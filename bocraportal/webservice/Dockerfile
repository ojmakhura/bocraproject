# Dockerfile
FROM openjdk:11

RUN mkdir -p /app
WORKDIR /app

ARG JAR_FILE=bocraportal/webservice/target/*.jar
COPY ${JAR_FILE} bocraportal-webservice.jar
ENTRYPOINT ["java","-jar","/app/bocraportal-webservice.jar"]
EXPOSE 8085
