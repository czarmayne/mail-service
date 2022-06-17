
# Mail Service

Looking for a mail service integration that will allow you to send to different mail providers?
Lucky you! We are now supporting 2 providers (_and maybe more in the future. So, stay tuned!_)

## Features

- Mailgun
- SendGrid

### RESTful API 
_see WIKI.md for more info_
- User Validation
- No auth, no additional 3rd Party Library except for the *Spring Framework*
- Mocked API for 2 providers
- Error Handling
- Fail-over (_no assessment yet which is better for the 2 provides in terms of cost_)

###  OPTIONAL
- [X] Swagger Documentation
//see WIKI.md for API Reference
- [X] An ARCHITECTURE.md with technical decisions
//see ARCHITECTURE.md
- [X] API Metrics
//added micrometer for easier pulling of metrics in case deployed in env and can be easily integrate with prometheus
- [X] API Resiliency
//added retry mechanism and graceful shutdown
- [X] Logging
//added traceability; was not able to implement traceheader or interceptors
- [ ] Deploying your solution somewhere (URL) for us to play with it.
//was not able to deploy in Heroku or RedHat Sandbox, but added instructions in the demo section below
- [ ] Security | JWT / Oauth2

## Tech Stack

- Java 11
- Gradle 7.4 & up
- Docker
- Helm
- Make / Ansible

## Run Locally

Clone the project

```bash
  git clone git@github.com:czarmayne/mail-service.gi
```

Go to the project directory

```bash
  cd my-project
```

Install dependencies

```bash
  curl -s "https://get.sdkman.io" | bash
  source "$HOME/.sdkman/bin/sdkman-init.sh"
  
  sdk install java 11.0.14-librca
  sdk install gradle 7.4
  brew install --cask docker
  brew install helm
  brew install make
  brew install ansible
```

Start the server

```bash
  make r
```
Test the API 
```bash
  make test
```

## Demo

To play with the app:
```
 //reference https://hub.docker.com/repository/docker/czaring/mail-service
    docker pull czaring/mail-service:0.0.2-SNAPSHOT
 
 // you can use the Makefile or use the commands that corresponds to the below ones:
    make dRun IMG=czaring/mail-service:0.0.2-SNAPSHOT
    make test
```
