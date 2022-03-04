
# port definition
app_port := 80
health_port := 81

# e.g. --args='spring.profiles.active=local'
profile :=

default: up test

b:
	@echo [START] build app
	gradle clean build

r: b
	@echo [START] run app
	gradle bootRun

dApp: b
	@echo [START] remote debug app
	gradle bootRun ${profile} --debug-jvm

# ensure to update the version if you don't want to override the existing
img:
	@echo [START] containerize app
	gradle clean build docker
	@echo [START] confirm containerized app
	docker images | grep 'mail'

imgP:
	@echo "[START] push containerized app in docker registry"
	gradle clean build dockerPush

# use this to remove 'none' images
imgC:
	@echo [START] docker image clean up
	@sudo ./clean.sh

# e.g. make dRun IMG=czaring/mail-service:0.0.1-SNAPSHOT
dRun:
	@echo [START] docker run
	docker run --name mail_service -d -p 80:8080 -p 81:8081 $(IMG)

up:
	@echo [START] check app health
	curl localhost:${health_port}/actuator/health
	@echo [START] check controller
	curl localhost:${app_port}/hello

test:
	@echo [START] send email
	curl --location --request POST 'localhost:${app_port}/mail/default' \
    --header 'Content-Type: application/json' \
    --data-raw '{ "recipients" : ["czar@gmail.com","czar@outlook.com"],"cc" : [""],"bcc": [""],"from":"czar@gmail.com", "message":"test","subject":"subj"}}'