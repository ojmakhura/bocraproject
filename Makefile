include ./Makefile.dev

mvn_build_mda:
	cd bocraportal/mda && mvn install -Dmaven.test.skip=true -o

mvn_build_core:
	cd bocraportal/core && mvn install -Dmaven.test.skip=true -o

mvn_build_api:
	cd bocraportal/webservice && mvn install -Dmaven.test.skip=true -o

mvn_build_web:
	cd bocraportal/webservice && mvn install -f angular -Dmaven.test.skip=true -o

mvn_build_all: 
	cd bocraportal &&  mvn install -Dmaven.test.skip=true -o

mvn_clean_build: gen_local_env mvn_clean_all mvn_build_all

mvn_clean_all:
	cd bocraportal && mvn clean -o

##
## Building and running on the local platform
##
build_local_api: gen_docker_env mvn_build_api
	docker-compose -f docker-compose-local.yml build api

build_local_web: gen_docker_env 
	docker-compose -f docker-compose-local.yml build web

build_local_db: gen_docker_env
	docker-compose -f docker-compose-local.yml build db

build_local_keycloak: gen_docker_env
	docker-compose -f docker-compose-local.yml build keycloak

build_local_proxy: gen_docker_env
	docker-compose -f docker-compose-local.yml build proxy

build_local_images: gen_docker_env mvn_build_all
	docker-compose -f docker-compose-local.yml build

up_local_app: rm_env gen_docker_env
	docker-compose -f docker-compose-local.yml up -d

up_local_db: rm_env gen_docker_env
	docker-compose -f docker-compose-local.yml up -d db

up_local_keycloak: rm_env gen_docker_env
	docker-compose -f docker-compose-local.yml up -d keycloak

up_local_proxy: rm_env gen_docker_env
	docker-compose -f docker-compose-local.yml up -d proxy

up_local_web: rm_env gen_docker_env
	docker-compose -f docker-compose-local.yml up -d web

up_local_pgadmin: rm_env gen_docker_env
	docker-compose -f docker-compose-local.yml up -d pgadmin

up_local_api: rm_env gen_docker_env
	docker-compose -f docker-compose-local.yml up -d api

up_local_registry: rm_env gen_docker_env
	docker-compose -f docker-compose-local.yml up -d registry

up_local_jenkins: rm_env gen_docker_env
	docker-compose -f docker-compose-local.yml up -d jenkins

run_local_app: build_local_images up_local_app

run_api_local: rm_env gen_local_env
	@$(LOCAL_ENV) && chmod 755 .env && . ./.env && cd bocraportal/webservice && mvn spring-boot:run

local_web_deps: rm_env gen_local_env mvn_build_all
	@$(LOCAL_ENV) && chmod 755 .env && . ./.env && cd bocraportal/angular/target/bocraportal && npm i

run_web_local: rm_env gen_local_env
	@$(LOCAL_ENV) && chmod 755 .env && . ./.env && cd bocraportal/angular/target/bocraportal && npm start

# run_local_web: build_local_images up_local_app
stop_local_app:
	docker-compose -f docker-compose-local.yml  down

rm_env:
	rm -f .env

gen_local_env: rm_env
	if [ -f .env ]; then \
		rm -f .env; \
	fi
	@$(LOCAL_ENV)

gen_docker_env: rm_env
	if [ -f .env ]; then \
		rm -f .env; \
	fi
	@$(LOCAL_DOCKER_ENV)

##
## Building and running on the test platform
##
build_live_api: gen_live_env mvn_build_api
	docker-compose build api

build_live_web: gen_live_env mvn_build_api
	docker-compose build web

build_live_images: gen_live_env mvn_build_all
	docker-compose build

up_live_app: 
	if [ -f /bocra/traefik/acme.json ]; then \ 
		rm -f /bocra/traefik/acme.json; \
	fi
	docker-compose up -d

run_live_app: build_live_all up_live_app

stop_live_app:
	docker-compose down

gen_live_env: 
	if [ -f .env ]; then \
		rm -f .env; \
	fi
	@$(LIVE_ENV)


##
## Check the logs
##
keycloak_logs:
	docker-compose logs keycloak

api_logs:
	docker-compose logs api

web_logs:
	docker-compose logs web

proxy_logs:
	docker-compose logs proxy

pgadmin_logs:
	docker-compose logs pgadmin

# queue_logs:
# 	docker-compose logs queue

# flower_logs:
# 	docker-compose logs flower

db_logs:
	docker-compose logs db