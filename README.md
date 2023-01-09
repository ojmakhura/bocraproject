# bocra

# Dependencies
## Doctor Jim
The project relies on Doctor Jim which should be cloned from https://github.com/ojmakhura/doctor-jim

## AndroMDA
The project relies on the AndroMDA framework to generate code from the UML diagram. Therefore, you should clone and compile the AndroMDA project located at https://github.com/ojmakhura/andromda

## Docker
Make sure you have docker installed on your system.

## Make
The build process for this application uses the make command. Make sure it has been installed on your system.

# Running the application locally (from scratch)
## Build the application
1. Run 'make clean_build'
2. If building for the first time, you may need to cd into the bocraportal directory and run 'mvn install -Dmaven.test.skip=true'

## Bring up keycloak server
1. Run 'make up_service run_env=LOCAL service=keycloak'. Validate that it id running through 'docker ps'
2. Once keycloak is running, open the URL 'keycloak.localhost'
3. Log onto the server using the credentials in Makefile.constants
4. Create a new realm, but load the file 'realm-export.json'
5. Change to the 'bocraportal' realm.
6. Add a user and add the roles from the bocraweb-client
7. Set the password credentials for the user.

## Bring up the pgadmin
1. Run 'make up_service run_env=LOCAL service=pgadmin'
2. Log into pgadmin.localhost using the credentials from Makefile.constants
3. Run the commands from initdb.sql
4. Upload the data from the 'data' directory into the tables of the same name as the files.

## Run the API locally
1. Run the API locally using 'make run_env=LOCAL run_api_local'

## Run the Communication Service locally
1. Run the COMm Service locally using 'make run_env=LOCAL run_comm_local'
## Run the API locally
1. Run the API locally using 'make run_env=LOCAL run_api_local'

## Run the frontend locally
1. Run the command 'make local_web_deps'
2. Run the command 'make run_web_local'

# Self Signed cert
## localhost domain
mkcert -key-file localhost.key -cert-file localhost.crt localhost keycloak.localhost api.localhost db.localhost proxy.localhost prometheus.localhost portainer.localhost unsee.localhost grafana.localhost *.localhost

## csdev.roguesystems.co.bw domain
mkcert -key-file csdev.roguesystems.co.bw.key -cert-file csdev.roguesystems.co.bw.crt csdev.roguesystems.co.bw keycloak.csdev.roguesystems.co.bw api.csdev.roguesystems.co.bw db.csdev.roguesystems.co.bw proxy.csdev.roguesystems.co.bw prometheus.csdev.roguesystems.co.bw portainer.csdev.roguesystems.co.bw unsee.csdev.roguesystems.co.bw grafana.csdev.roguesystems.co.bw *.csdev.roguesystems.co.bw

# Import self signed certs on for running api locally
keytool -importcert -file /home/junior/bocra/certs/localhost.crt -noprompt -alias localhostbocra -storepass changeit -keystore /usr/lib/jvm/java-1.11.0-openjdk-amd64/lib/security/cacerts
