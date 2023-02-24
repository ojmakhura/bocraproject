# Dockerfile
FROM ghcr.io/graalvm/jdk:ol9-java17-22.3.0-b2

ARG CERT
ARG CERT_PASSWORD
ARG IMAGE_VERSION

ENV CERT ${CERT}
ENV CERT_PASSWORD ${CERT_PASSWORD}

RUN mkdir -p /app
WORKDIR /app

COPY deployment/certs/bocradev.local.crt /app/certs/bocradev.local.crt
COPY deployment/certs/localhost.crt /app/certs/localhost.crt
COPY deployment/certs/bocra_org_bw.crt /app/certs/bocra_org_bw.crt
COPY deployment/certs/STAR_csdev_roguesystems_co_bw.crt /app/certs/STAR_csdev_roguesystems_co_bw.crt
RUN keytool -importcert -file /app/certs/bocradev.local.crt -noprompt -alias bocradev -storepass changeit -keystore ${JAVA_HOME}/lib/security/cacerts
RUN keytool -importcert -file /app/certs/localhost.crt -noprompt -alias local -storepass changeit -keystore ${JAVA_HOME}/lib/security/cacerts
RUN keytool -importcert -file /app/certs/bocra_org_bw.crt -noprompt -alias bocra -storepass changeit -keystore ${JAVA_HOME}/lib/security/cacerts
RUN keytool -importcert -file /app/certs/STAR_csdev_roguesystems_co_bw.crt -noprompt -alias dev -storepass changeit -keystore ${JAVA_HOME}/lib/security/cacerts

ARG JAR_FILE=bocraportal/cron/target/bocraportal-cron-${IMAGE_VERSION}.jar
COPY ${JAR_FILE} bocraportal-cron.jar
ENTRYPOINT ["java", "-jar", "/app/bocraportal-cron.jar"]
EXPOSE 8099
