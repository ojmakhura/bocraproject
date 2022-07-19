include ./Makefile.dev

build_mda:
	mvn -f bocraportal/mda install -Dmaven.test.skip=true -o

build_common:
	mvn -f bocraportal/common install -Dmaven.test.skip=true -o

build_core:
	mvn -f bocraportal/core install -Dmaven.test.skip=true -o

build_api:
	@$(LOCAL_ENV) && chmod 755 .env && . ./.env && cd bocraportal/webservice && mvn install -o

build_web:
	mvn -f bocraportal/angular install -Dmaven.test.skip=true -o

build_web_dist: rm_env gen_local_env build_web local_web_deps
	@$(LOCAL_ENV) && chmod 755 .env && . ./.env && cd bocraportal/angular/target/bocraportal && npm run build --configuration=production

build_all: 
	mvn -f bocraportal install -Dmaven.test.skip=true -o

clean_build: gen_local_env clean_all build_all

clean_all:
	mvn -f bocraportal clean -o

clean_mda:
	mvn -f bocraportal/mda clean -o

##
## Build docker images
##
build_api_image: build_api
	docker-compose build api

build_web_image: build_web_dist
	docker-compose build web

build_db_image: gen_docker_env
	docker-compose build db

build_keycloak_image: gen_docker_env
	docker-compose build keycloak

build_proxy_image: gen_docker_env
	docker-compose build proxy

build_images: gen_docker_env build_all
	docker-compose build

##
## Building and running on the local platform
##
build_local_api: gen_docker_env build_api build_api_image

build_local_web: gen_docker_env build_web_image

build_local_db: gen_docker_env build_db_image

build_local_keycloak: gen_docker_env build_keycloak_image

build_local_proxy: gen_docker_env build_proxy_image

build_local_images: gen_docker_env build_images
	docker-compose build

up_local_app: rm_env gen_docker_env build_local_images
	docker-compose up -d

up_local_db: rm_env gen_docker_env
	docker-compose up -d db

up_local_keycloak: rm_env gen_docker_env
	docker-compose up -d keycloak

up_local_proxy: rm_env gen_docker_env
	docker-compose up -d proxy

up_local_web: rm_env gen_docker_env
	docker-compose up -d web

up_local_pgadmin: rm_env gen_docker_env
	docker-compose up -d pgadmin

up_local_api: rm_env gen_docker_env
	docker-compose up -d api

up_local_registry: rm_env gen_docker_env
	docker-compose up -d registry

up_local_jenkins: rm_env gen_docker_env
	docker-compose up -d jenkins

run_local_app: build_local_images up_local_app

run_api_local: rm_env gen_local_env
	@$(LOCAL_ENV) && chmod 755 .env && . ./.env && cd bocraportal/webservice && mvn spring-boot:run

local_web_deps: rm_env gen_local_env build_web
	@$(LOCAL_ENV) && chmod 755 .env && . ./.env && cd bocraportal/angular/target/bocraportal && npm i

run_web_local: rm_env gen_local_env
	@$(LOCAL_ENV) && chmod 755 .env && . ./.env && cd bocraportal/angular/target/bocraportal && npm start

# run_local_web: build_local_images up_local_app
stop_local_app:
	docker-compose -f  down

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
build_test_api: gen_test_docker_env build_api build_api_image

build_test_web: gen_test_docker_env build_web 
	docker-compose build web

build_test_db: gen_test_docker_env
	docker-compose build db

build_test_keycloak: gen_test_docker_env
	docker-compose build keycloak

build_test_proxy: gen_test_docker_env
	docker-compose build proxy

build_test_images: gen_test_docker_env build_all
	docker-compose build

gen_test_docker_env: rm_env
	if [ -f .env ]; then \
		rm -f .env; \
	fi
	@$(LOCAL_DOCKER_ENV)

##
## Building and running on the live platform
##
build_live_api: gen_live_env build_api build_api_image

build_live_web: gen_live_env build_api build_web_image

build_live_images: gen_live_env build_all build_images

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

db_logs:
	docker-compose logs db