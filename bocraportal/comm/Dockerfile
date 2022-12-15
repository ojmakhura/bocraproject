# Dockerfile
FROM openjdk:17

ARG CERT
ARG CERT_PASSWORD

ENV CERT ${CERT}
ENV CERT_PASSWORD ${CERT_PASSWORD}

RUN mkdir -p /app
WORKDIR /app

COPY deployment/certs/${CERT}.crt /app/certs/${CERT}.crt
RUN keytool -importcert -file /app/certs/${CERT}.crt -noprompt -alias bocra -storepass ${CERT_PASSWORD} -keystore ${JAVA_HOME}/lib/security/cacerts

ARG JAR_FILE=bocraportal/comm/target/*.jar
COPY ${JAR_FILE} bocraportal-comm.jar
ENTRYPOINT ["java","-jar","/app/bocraportal-comm.jar"]
EXPOSE 8085
