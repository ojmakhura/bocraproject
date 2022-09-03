include ./Makefile.dev

gen_self_certs: gen_env
	. ./.env && sudo rm ${BOCRA_DATA}/traefik/${DOMAIN}.crt && . ./.env && sudo rm ${BOCRA_DATA}/traefik/${DOMAIN}.key && . ./.env && sudo openssl req -x509 -sha256 -days 356 -nodes -newkey rsa:2048 -out ${BOCRA_DATA}/traefik/${DOMAIN}.crt -keyout ${BOCRA_DATA}/traefik/${DOMAIN}.key

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

clean_build: clean_all build_all

clean_all:
	mvn -f bocraportal clean -o

clean_mda:
	mvn -f bocraportal/mda clean -o

##
## Start the docker containers
##
up_full_app: gen_env
	. ./.env && docker compose up -d

up_db:gen_env
	. ./.env && docker compose up -d db

up_keycloak: gen_env
	. ./.env && docker compose up -d keycloak

up_proxy: gen_env
	. ./.env && docker compose up -d proxy

up_web: gen_env
	. ./.env && docker compose up -d web

up_pgadmin: gen_env
	. ./.env && docker compose up -d pgadmin

up_api: gen_env
	. ./.env && docker compose up -d api

up_registry: gen_env
	. ./.env && docker compose up -d registry

up_jenkins: gen_env
	. ./.env && docker compose up -d jenkins

##
## Build docker images
##
build_api_image: build_api
	. ./.env && docker compose build api

build_web_image: gen_env
	docker compose build web

build_keycloak_image: gen_env
	. ./.env && docker compose build keycloak


build_images: gen_env build_app
	. ./.env && docker compose build

###
## puch the images
###
push_web_image: gen_env
	. ./.env && docker push ${REGISTRY_TAG}/${WEB_IMAGE_NAME}:latest${IMAGE_VERSION_SUFFIX}

push_api_image: gen_env
	. ./.env && docker push ${REGISTRY_TAG}/${API_IMAGE_NAME}:latest${IMAGE_VERSION_SUFFIX}

push_keycloak_image: gen_env
	. ./.env && echo ${KEYCLOAK_REGISTRY_IMAGE}
	. ./.env && docker push ${KEYCLOAK_REGISTRY_IMAGE}

###
## Run the local api and web
###
run_api_local: gen_env
	. ./.env && cd bocraportal/webservice && mvn spring-boot:run

local_web_deps: gen_env build_web
	. ./.env && cd bocraportal/angular/target/bocraportal && npm i

run_web_local: gen_env build_web
	. ./.env && cd bocraportal/angular/target/bocraportal && npm start

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
	@$(${run_env})
	chmod 755 .env
else
	@echo 'no run_env defined. Please run again with `make run_env=<LOCAL_ENV, DEV_ENV, TEST_ENV, LIVE_ENV> target`'
	exit 1
endif

##
## Swarm initialisation
##
swarm_init:
	docker swarm init

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