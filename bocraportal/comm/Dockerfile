# Dockerfile
FROM eclipse-temurin:17

ARG CERT
ARG CERT_PASSWORD
ARG IMAGE_VERSION

ENV CERT ${CERT}
ENV CERT_PASSWORD ${CERT_PASSWORD}

RUN mkdir -p /app
WORKDIR /app

COPY deployment/certs/localhost.crt /app/certs/localhost.crt
COPY deployment/certs/bocra_org_bw.crt /app/certs/bocra_org_bw.crt
COPY deployment/certs/STAR_csdev_roguesystems_co_bw.crt /app/certs/STAR_csdev_roguesystems_co_bw.crt
RUN keytool -importcert -file /app/certs/localhost.crt -noprompt -alias local -storepass ${CERT_PASSWORD} -keystore ${JAVA_HOME}/lib/security/cacerts
RUN keytool -importcert -file /app/certs/bocra_org_bw.crt -noprompt -alias bocra -storepass ${CERT_PASSWORD} -keystore ${JAVA_HOME}/lib/security/cacerts
RUN keytool -importcert -file /app/certs/STAR_csdev_roguesystems_co_bw.crt -noprompt -alias dev -storepass ${CERT_PASSWORD} -keystore ${JAVA_HOME}/lib/security/cacerts

ARG JAR_FILE=bocraportal/comm/target/*.jar
COPY ${JAR_FILE} bocraportal-comm.jar
ENTRYPOINT ["java","-jar","/app/bocraportal-comm.jar"]
EXPOSE 8095
