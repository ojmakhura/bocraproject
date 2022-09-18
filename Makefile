include ./Makefile.dev

gen_self_certs:
ifdef domain
	mkcert -key-file deployment/certs/${domain}.key -cert-file deployment/certs/${domain}.crt ${domain} keycloak.${domain} api.${domain} db.${domain} proxy.${domain} prometheus.${domain} portainer.${domain} unsee.${domain} grafana.${domain} *.${domain}
else
	mkcert -key-file deployment/certs/localhost.key -cert-file deployment/certs/localhost.crt localhost keycloak.localhost api.localhost db.localhost proxy.localhost prometheus.localhost portainer.localhost unsee.localhost grafana.localhost *.localhost
endif
	

build_mda:
	mvn -f bocraportal/mda install -Dmaven.test.skip=true -o

build_common:
	mvn -f bocraportal/common install -Dmaven.test.skip=true -o

build_core:
	mvn -f bocraportal/core install -Dmaven.test.skip=true -o

build_api:
	mvn -f bocraportal/webservice install -Dmaven.test.skip=true -o

build_web: 
	mvn -f bocraportal/angular install -Dmaven.test.skip=true -o

build_web_dist: build_web local_web_deps
	. ./.env && cd bocraportal/angular/target/bocraportal && npm run build --configuration=production

build_app: 
	mvn -f bocraportal install -Dmaven.test.skip=true -o

clean_build: clean_all build_app

clean_all:
	mvn -f bocraportal clean -o

clean_mda:
	mvn -f bocraportal/mda clean -o

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
	
##
## Build docker images
##
build_image: gen_env
	. ./.env && docker compose -f ${stack_file}.yml build

build_api_image: build_api gen_env
	. ./.env && docker compose build --build-arg CERT=${CERT} --build-arg CERT_PASSWORD=${CERT_PASSWORD} api

build_web_image: gen_env
	. ./.env && docker compose build web

build_keycloak_image: gen_env
	. ./.env && docker compose -f docker-compose-keycloak.yml build


build_images: gen_env build_keycloak_image build_web_image build_api_image

###
## tag and push the images
###
push_web_image: gen_env
	. ./.env && docker push ${REGISTRY_TAG}/${WEB_IMAGE_NAME}:${IMAGE_VERSION}${IMAGE_VERSION_SUFFIX}

push_api_image: gen_env
	. ./.env && docker push ${REGISTRY_TAG}/${API_IMAGE_NAME}:${IMAGE_VERSION}${IMAGE_VERSION_SUFFIX}

push_keycloak_image: gen_env
	. ./.env && echo ${KEYCLOAK_REGISTRY_IMAGE}
	. ./.env && docker push ${KEYCLOAK_REGISTRY_IMAGE}

###
## Run the local api and web
###
run_api_local: gen_env
	. ./.env && cd bocraportal/webservice && mvn spring-boot:run

local_web_deps: build_web
	cd bocraportal/angular/target/bocraportal && npm i

run_web_local: build_web
	cd bocraportal/angular/target/bocraportal && npm start

# run_local_web: build_local_images up_local_app
stop_app:
	docker compose down

rm_env:
	rm -f .env

gen_env: rm_env
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
