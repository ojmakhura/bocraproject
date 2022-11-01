# Dockerfile
FROM openjdk:11

ARG CERT
ARG CERT_PASSWORD

ENV CERT ${CERT}
ENV CERT_PASSWORD ${CERT_PASSWORD}

RUN mkdir -p /app
WORKDIR /app
RUN echo ${CERT}
RUN echo ${JAVA_HOME}

COPY deployment/certs/${CERT}.crt /app/certs/${CERT}.crt
RUN keytool -importcert -file /app/certs/${CERT}.crt -noprompt -alias bocra -storepass ${CERT_PASSWORD} -keystore ${JAVA_HOME}/lib/security/cacerts

ARG JAR_FILE=bocraportal/webservice/target/*.jar
COPY ${JAR_FILE} bocraportal-webservice.jar
ENTRYPOINT ["java","-jar","/app/bocraportal-webservice.jar"]
EXPOSE 8085
