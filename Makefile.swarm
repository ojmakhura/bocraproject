include ./Makefile.dev

gen_self_certs:
	chmod 755 .env && . ./.env && sudo rm ${BOCRA_DATA}/traefik/${DOMAIN}.crt && chmod 755 .env && . ./.env && sudo rm ${BOCRA_DATA}/traefik/${DOMAIN}.key && chmod 755 .env && . ./.env && sudo openssl req -x509 -sha256 -days 356 -nodes -newkey rsa:2048 -out ${BOCRA_DATA}/traefik/${DOMAIN}.crt -keyout ${BOCRA_DATA}/traefik/${DOMAIN}.key

build_mda:
	mvn -f bocraportal/mda install -Dmaven.test.skip=true -o

build_common:
	mvn -f bocraportal/common install -Dmaven.test.skip=true -o

build_core:
	mvn -f bocraportal/core install -Dmaven.test.skip=true -o

build_api: 
	chmod 755 .env && . ./.env && mvn -f bocraportal/webservice install -o

build_web:
	mvn -f bocraportal/angular install -Dmaven.test.skip=true -o

build_web_dist: build_web local_web_deps
	chmod 755 .env && . ./.env && cd bocraportal/angular/target/bocraportal && npm run build --configuration=production

build_all: 
	mvn -f bocraportal install -Dmaven.test.skip=true -o

clean_build: clean_all build_all

clean_all:
	mvn -f bocraportal clean -o

clean_mda:
	mvn -f bocraportal/mda clean -o

##
## Start the docker containers
##
up_full_app: up_proxy up_db

up_db:
	chmod 755 .env && . ./.env && docker stack deploy -c docker-compose-db.yml ${STACK_NAME}-db

down_db:
	docker stack rm ${STACK_NAME}-db

up_keycloak: build_keycloak_image
	chmod 755 .env && . ./.env && docker stack deploy -c docker-compose-keycloak.yml ${STACK_NAME}-keycloak

down_keycloak:
	docker stack rm ${STACK_NAME}-keycloak

up_proxy: 
	chmod 755 .env && . ./.env && docker stack deploy -c docker-compose-traefik.yml ${STACK_NAME}-proxy

down_proxy:
	docker stack rm ${STACK_NAME}-proxy

up_web:
	chmod 755 .env && . ./.env && docker stack deploy -c docker-compose-web.yml ${STACK_NAME}-web

down_web:
	docker stack rm ${STACK_NAME}-web

# up_pgadmin: 
# 	chmod 755 .env && . ./.env && docker stack deploy -c docker-compose-web.yml ${STACK_NAME}-web
# 	chmod 755 .env && . ./.env && docker compose up -d pgadmin

up_api: 
	chmod 755 .env && . ./.env && docker stack deploy -c docker-compose-api.yml ${STACK_NAME}-api

down_api:
	docker stack rm ${STACK_NAME}-api

up_registry:
	chmod 755 .env && . ./.env && docker compose up -d registry

up_jenkins:
	chmod 755 .env && . ./.env && docker compose up -d jenkins

##
## Build docker images
##
build_api_image: build_api
	chmod 755 .env && . ./.env && docker compose -f docker-compose-api.yml build api

build_web_image:
	docker compose build web

build_db_image: 
	chmod 755 .env && . ./.env && docker compose build db

build_keycloak_image: 
	chmod 755 .env && . ./.env && docker compose -f docker-compose-keycloak.yml build

build_proxy_image: 
	chmod 755 .env && . ./.env && docker compose build proxy

build_images: build_all
	chmod 755 .env && . ./.env && docker compose build

#################################################################################
## Building and running on the local platform
#################################################################################
build_local_api: gen_env build_api build_api_image 

build_local_web: gen_env build_web_image 

build_local_db: gen_env build_db_image 

build_local_keycloak: gen_env build_keycloak_image 

build_local_proxy: gen_env build_proxy_image 

build_local_images: gen_env build_images 

###
## Run the local images
###
up_local_app: build_local_images up_full_app

up_local_db: gen_env up_db

up_local_keycloak: gen_env up_keycloak

up_local_proxy: gen_env up_proxy

up_local_web: gen_env up_web

up_local_pgadmin: gen_env up_pgadmin

up_local_api: gen_env up_api

up_local_registry: gen_env up_registry

up_local_jenkins: gen_env up_jenkins

run_local_app: gen_env build_local_images up_local_app

run_api_local: gen_env
	@$(LOCAL_ENV) && chmod 755 .env && . ./.env && cd bocraportal/webservice && mvn spring-boot:run

local_web_deps: gen_env build_web
	@$(LOCAL_ENV) && chmod 755 .env && . ./.env && cd bocraportal/angular/target/bocraportal && npm i

run_web_local: gen_env build_web
	@$(LOCAL_ENV) && chmod 755 .env && . ./.env && cd bocraportal/angular/target/bocraportal && npm start

# run_local_web: build_local_images up_local_app
stop_app:
	docker compose down

rm_env:
	rm -f .env

gen_env: rm_env
	if [ -f .env ]; then \
		rm -f .env; \
	fi
	@$(LOCAL_ENV)

gen_docker_env: rm_env
	if [ -f .env ]; then \
		rm -f .env; \
	fi
	@$(LOCAL_DOCKER_ENV)

#################################################################################
## Building and running on the test platform
#################################################################################
build_test_api: gen_test_env build_api build_api_image 

build_test_web: gen_test_env build_web_image 

build_test_db: gen_test_env build_db_image 

build_test_keycloak: gen_test_env build_keycloak_image 

build_test_proxy: gen_test_env build_proxy_image 

build_test_images: gen_test_env build_images 

###
## Run the local images
###
up_test_app: build_test_images up_full_app

up_test_db: gen_test_env up_db

up_test_keycloak: gen_test_env up_keycloak

up_test_proxy: gen_test_env up_proxy

up_test_web: gen_test_env up_web

up_test_pgadmin: gen_test_env up_pgadmin

up_test_api: gen_test_env up_api

up_test_registry: gen_test_env up_registry

up_test_jenkins: gen_test_env up_jenkins

run_test_app: gen_test_env build_test_images up_test_app

gen_test_env: rm_env
	if [ -f .env ]; then \
		rm -f .env; \
	fi
	@$(TEST_ENV)

#################################################################################
## Building and running on the development test platform
#################################################################################
build_dev_api: gen_dev_env build_api build_api_image 

build_dev_web: gen_dev_env build_web_image 

build_dev_db: gen_dev_env build_db_image 

build_dev_keycloak: gen_dev_env build_keycloak_image 

build_dev_proxy: gen_dev_env build_proxy_image 

build_dev_images: gen_dev_env build_images 

###
## Run the dev images
###
up_dev_app: build_dev_images up_full_app

up_dev_db: gen_dev_env up_db

up_dev_keycloak: gen_dev_env up_keycloak

up_dev_proxy: gen_dev_env up_proxy

up_dev_web: gen_dev_env up_web

up_dev_pgadmin: gen_dev_env up_pgadmin

up_dev_api: gen_dev_env up_api

up_dev_registry: gen_dev_env up_registry

up_dev_jenkins: gen_dev_env up_jenkins

run_dev_app: gen_dev_env build_dev_images up_dev_app

gen_dev_env: rm_env
	if [ -f .env ]; then \
		rm -f .env; \
	fi
	@$(DEV_ENV)

##
## Swarm initialisation
##
swarm_init:
	docker swarm init

##
## Check the logs
##
keycloak_logs:
	docker service logs ${STACK_NAME}-keycloak_keycloak

api_logs:
	docker service logs ${STACK_NAME}-api_api

web_logs:
	docker service logs ${STACK_NAME}-web_web

proxy_logs:
	docker service logs ${STACK_NAME}-proxy_proxy

pgadmin_logs:
	docker service logs ${STACK_NAME}-db_pgadmin

db_logs:
	docker service logs ${STACK_NAME}-db_db
	
jenkins_logs:
	docker compose logs jenkins
registry_logs:
	docker compose logs registry

traefik_network:
	docker network create --driver overlay traefik-public