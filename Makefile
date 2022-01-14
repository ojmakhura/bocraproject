include ./Makefile.dev

mvn_build_core:
	cd bocra-core && mvn clean install -Dmaven.test.skip=true

mvn_build_api:
	cd bocra-api && mvn clean install -Dmaven.test.skip=true

mvn_build_pipeline:
	cd bocra-pipeline && mvn clean install -Dmaven.test.skip=true

mvn_build_all: mvn_build_core mvn_build_api mvn_build_pipeline

##
## Building and running on the local platform
##
build_local_api: gen_local_env mvn_build_api
	docker-compose -f docker-compose-local.yml build api

build_local_pipeline: gen_local_env mvn_build_pipeline
	docker-compose -f docker-compose-local.yml build pipeline

build_local_web: gen_local_env mvn_build_all
	docker-compose -f docker-compose-local.yml build web

build_local_images: gen_local_env mvn_build_all
	docker-compose -f docker-compose-local.yml build

up_local_app: 
	docker-compose -f docker-compose-local.yml up -d

run_local_app: build_local_images up_local_app

stop_local_app:
	docker-compose -f docker-compose-local.yml  down

gen_local_env: 
	if [ -f .env ]; then \
		rm -f .env; \
	fi
	@$(LOCAL_ENV)

##
## Building and running on the test platform
##
build_test_api: gen_test_env mvn_build_api
	docker-compose build api

build_test_pipeline: gen_test_env mvn_build_pipeline
	docker-compose build pipeline

build_test_web: gen_test_env mvn_build_all
	docker-compose build web

build_test_images: gen_test_env mvn_build_all
	docker-compose build

up_test_app: 
	if [ -f /bocra/traefik/acme.json ]; then \
		rm -f /bocra/traefik/acme.json; \
	fi
	docker-compose up -d

run_test_app: build_test_images up_test_app

stop_test_app:
	docker-compose down

gen_test_env: 
	if [ -f .env ]; then \
		rm -f .env; \
	fi
	@$(TEST_ENV)

##
## Building and running on the test platform
##
build_live_api: gen_live_env mvn_build_api
	docker-compose build api

build_live_pipeline: gen_live_env mvn_build_pipeline
	docker-compose build pipeline

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

pipeline_logs:
	docker-compose logs pipeline

web_logs:
	docker-compose logs web

proxy_logs:
	docker-compose logs proxy

pgadmin_logs:
	docker-compose logs pgadmin

queue_logs:
	docker-compose logs queue

flower_logs:
	docker-compose logs flower

db_logs:
	docker-compose logs db