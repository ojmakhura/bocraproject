# Dockerfile
FROM eclipse-temurin:17

ARG CERT
ARG CERT_PASSWORD

ENV CERT ${CERT}
ENV CERT_PASSWORD ${CERT_PASSWORD}

RUN mkdir -p /app
WORKDIR /app

COPY deployment/certs/${CERT}.crt /app/certs/${CERT}.crt
RUN keytool -importcert -file /app/certs/localhost.crt -noprompt -alias local -storepass ${CERT_PASSWORD} -keystore ${JAVA_HOME}/lib/security/cacerts
RUN keytool -importcert -file /app/certs/bocra_org_bw.crt -noprompt -alias bocra -storepass ${CERT_PASSWORD} -keystore ${JAVA_HOME}/lib/security/cacerts
RUN keytool -importcert -file /app/certs/STAR_csdev_roguesystems_co_bw.crt -noprompt -alias dev -storepass ${CERT_PASSWORD} -keystore ${JAVA_HOME}/lib/security/cacerts

ARG JAR_FILE=bocraportal/webservice/target/*.jar
COPY ${JAR_FILE} bocraportal-webservice.jar
ENTRYPOINT ["java","-jar","/app/bocraportal-webservice.jar"]
EXPOSE 8085
