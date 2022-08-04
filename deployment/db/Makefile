include ./Makefile.dev

rm_env:
	rm -f .env

##
## Start the docker containers
##
up_full_app: 
	chmod 755 .env && . ./.env && docker compose -f docker-compose-db.yml up -d

up_db:
	chmod 755 .env && . ./.env && docker compose -f docker-compose-db.yml up -d db

up_proxy: 
	chmod 755 .env && . ./.env && docker compose -f docker-compose-db.yml up -d proxy

up_pgadmin: 
	chmod 755 .env && . ./.env && docker compose -f docker-compose-db.yml up -d pgadmin


##
## Build docker images
##

build_db_image: 
	chmod 755 .env && . ./.env && docker compose -f docker-compose-db.yml build db

build_proxy_image: 
	chmod 755 .env && . ./.env && docker compose -f docker-compose-db.yml build proxy

build_images:
	chmod 755 .env && . ./.env && docker compose -f docker-compose-db.yml build

# run_local_web: build_local_images up_local_app
stop_app:
	docker compose -f docker-compose-db.yml down 

#################################################################################
## Building and running on the test platform
#################################################################################
build_test_db: gen_test_env build_db_image 

build_test_proxy: gen_test_env build_proxy_image 

build_test_images: gen_test_env build_images 

###
## Run the local images
###
up_test_app: up_full_app

up_test_db: gen_test_env up_db

up_test_proxy: gen_test_env up_proxy

up_test_pgadmin: gen_test_env up_pgadmin

run_test_app: gen_test_env up_test_app

gen_test_env: rm_env
	if [ -f .env ]; then \
		rm -f .env; \
	fi
	@$(TEST_ENV)


#################################################################################
## Building and running on the development test platform
#################################################################################
build_dev_db: gen_dev_env build_db_image 

build_dev_proxy: gen_dev_env build_proxy_image 

build_dev_images: gen_dev_env build_images 

###
## Run the dev images
###
up_dev_app: gen_dev_env up_full_app

up_dev_db: gen_dev_env up_db

up_dev_proxy: gen_dev_env up_proxy

up_dev_pgadmin: gen_dev_env up_pgadmin

run_dev_app: gen_dev_env up_dev_app

gen_dev_env: rm_env
	if [ -f .env ]; then \
		rm -f .env; \
	fi
	@$(DEV_ENV)

##
## Check the logs
##

proxy_logs:
	docker compose -f docker-compose-db.yml logs proxy

pgadmin_logs:
	docker compose -f docker-compose-db.yml logs pgadmin

db_logs:
	docker compose -f docker-compose-db.yml logs db
