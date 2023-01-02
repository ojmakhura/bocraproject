include ./Makefile.dev

gen_self_certs:
ifdef domain
	mkcert -key-file deployment/certs/${domain}.key -cert-file deployment/certs/${domain}.crt ${domain} keycloak.${domain} api.${domain} db.${domain} proxy.${domain} prometheus.${domain} portainer.${domain} unsee.${domain} grafana.${domain} *.${domain}
else
	mkcert -key-file deployment/certs/localhost.key -cert-file deployment/certs/localhost.crt localhost keycloak.localhost api.localhost db.localhost proxy.localhost prometheus.localhost portainer.localhost unsee.localhost grafana.localhost *.localhost
endif
	

build_mda:
	mvn -f bocraportal/mda install -DskipTests=true -o

build_common:
	mvn -f bocraportal/common install -DskipTests=true -o

build_core:
	mvn -f bocraportal/core install -DskipTests=true -o

test_core: 
	. ./.env && mvn -f bocraportal/core test -o

build_api:
	mvn -f bocraportal/webservice install -DskipTests=true -o

test_api: 
	. ./.env && mvn -f bocraportal/webservice test -o

build_comm:
	. ./.env && mvn -f bocraportal/comm -Pnative clean install -DskipTests -o

build_comm_native:
	mvn -f bocraportal/comm -Pnative native:compile -DskipTests -o

test_comm: 
	. ./.env && mvn -f bocraportal/comm test -o
	
build_cron: gen_env
	. ./.env && mvn -f bocraportal/cron -Pnative clean install -DskipTests -o
	
build_cron_native: gen_env
	. ./.env && mvn -f bocraportal/cron -Pnative native:compile -DskipTests -o
	
build_native: gen_env 
	. ./.env && mvn -f bocraportal/${module} clean native:compile -Pnative -DskipTests -o

colon = :
native_image_tracing: gen_env
	. ./.env && timeout 40 ${JAVA_HOME}/bin/java -agentlib${colon}native-image-agent=config-output-dir=./bocraportal/${service}/src/main/resources/META-INF/native-image -jar ./bocraportal/${service}/target/bocraportal-${service}-${IMAGE_VERSION}.jar
	
test_cron: gen_env
	. ./.env && mvn -f bocraportal/cron test -o

build_web: 
	mvn -f bocraportal/angular install -DskipTests=true -o

build_web_dist: build_web local_web_deps
	. ./.env && cd bocraportal/angular/target/bocraportal && npm run build --configuration=production

build_app: 
	mvn -f bocraportal install -DskipTests=true -o

clean_build: clean_all build_app

clean_all:
	mvn -f bocraportal clean -o

clean_mda:
	mvn -f bocraportal/mda clean -o

clean_cron:
	mvn -f bocraportal/cron clean -o

clean_module:
	mvn -f bocraportal/${service} clean -o

##
## Start the docker containers
##
up_proxy: gen_env
	. ./.env && docker compose up -d proxy

up_service: gen_env
ifdef service
	. ./.env && docker compose up -d ${service}
else
	@echo 'no service defined. Please run again with `make  service=<name> up_service`'
	exit 1
endif

down_service:
ifdef service
	docker stop ${STACK_NAME}-${service}
else
	@echo 'no run_env defined. Please run again with `make run_env=<LOCAL, DEV, TEST, LIVE> service=<name> down_service`'
	exit 1
endif

run_tests: gen_env test_${module}

run_test:
	. ./.env && mvn -f bocraportal/${module} -Dtest=${test} test -o
	
##
## Build docker images
##
build_image: gen_env
	. ./.env && docker compose -f ${stack_file}.yml build

build_api_image: gen_env build_api
	. ./.env && docker compose build api

build_comm_image: gen_env
	. ./.env && docker compose build comm

build_cron_image: gen_env 
	. ./.env && docker compose build cron
	# . ./.env && mvn -f bocraportal/cron spring-boot:build-image -DskipTests -o

build_web_image: gen_env
	. ./.env && docker compose build web

build_keycloak_image: gen_env
	. ./.env && docker compose -f docker-compose-keycloak.yml build


build_images: gen_env build_keycloak_image build_web_image build_api_image build_comm_image

###
## tag and push the images
###
push_web_image: gen_env
	. ./.env && docker push ${REGISTRY_TAG}/${WEB_IMAGE_NAME}:${IMAGE_VERSION}${IMAGE_VERSION_SUFFIX}

push_api_image: gen_env
	. ./.env && docker push ${REGISTRY_TAG}/${API_IMAGE_NAME}:${IMAGE_VERSION}${IMAGE_VERSION_SUFFIX}

push_comm_image: gen_env
	. ./.env && docker push ${REGISTRY_TAG}/${COMM_IMAGE_NAME}:${IMAGE_VERSION}${IMAGE_VERSION_SUFFIX}

push_keycloak_image: gen_env
	. ./.env && echo ${KEYCLOAK_REGISTRY_IMAGE}
	. ./.env && docker push ${KEYCLOAK_REGISTRY_IMAGE}

###
## Run the local api and web
###    
run_module_local: gen_env
	. ./.env && cd bocraportal/${module} && mvn spring-boot:run
	
run_api_local: gen_env
	. ./.env && cd bocraportal/webservice && mvn spring-boot:run

run_comm_local: gen_env
	. ./.env && cd bocraportal/comm && mvn spring-boot:run

run_cron_local: gen_env
	. ./.env && cd bocraportal/cron && mvn spring-boot:run

run_cron_native_local: gen_env
	. ./.env && bocraportal/cron/target/bocraportal-cron

local_web_deps: build_web
	cd bocraportal/angular/target/bocraportal && npm i

run_web_local: build_web
	cd bocraportal/angular/target/bocraportal && npm start

# run_local_web: build_local_images up_local_app
stop_app:
	docker compose down

rm_env:
	rm -f .env

gen_env:
ifdef run_env
	if [ -f .env ]; then \
		rm -f .env; \
	fi
	@$(${run_env}_ENV)
	chmod 755 .env
else
	@echo 'no run_env defined. Please run again with `make run_env=<LOCAL_ENV, DEV_ENV, TEST_ENV, LIVE_ENV> target`'
	exit 1
endif

##
## System initialisation
##
swarm_init:
	docker swarm init

local_prep: gen_env
	. ./.env && mkdir ${BOCRA_DATA}
	. ./.env && mkdir ${BOCRA_DATA}/db
	. ./.env && mkdir ${BOCRA_DATA}/auth
	. ./.env && cp traefik_passwd ${BOCRA_DATA}/auth/system_passwd
	. ./.env && mkdir ${BOCRA_DATA}/keycloak
	. ./.env && mkdir ${BOCRA_DATA}/certs
	. ./.env && cp deployment/certs/* ${BOCRA_DATA}/certs
	. ./.env && mkdir ${BOCRA_DATA}/registry
	. ./.env && mkdir ${BOCRA_DATA}/traefik
	. ./.env && cp deployment/traefik/config.yml ${BOCRA_DATA}/traefik
	. ./.env && mkdir ${BOCRA_DATA}/web

##
## Check the logs
##
keycloak_logs:
	docker compose logs keycloak

api_logs:
	docker compose logs api

comm_logs:
	docker compose logs comm

web_logs:
	docker compose logs web

proxy_logs:
	docker compose logs proxy

pgadmin_logs:
	docker compose logs pgadmin

db_logs:
	docker compose logs db
	
jenkins_logs:
	docker compose logs jenkins
registry_logs:
	docker compose logs registry

traefik_network:
	docker network create --driver overlay traefik-public
