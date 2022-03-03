
# Mail Service

Looking for a mail service integration that will allow you to send to different mail providers?
Lucky you! We are now supporting 2 providers (_and maybe more in the future. So, stay tuned!_)

## Features

- Mailgun
- SendGrid

## TODO

### RESTful API
- User Validation
- No auth, no additional 3rd Party Library
- Mocked API for 2 providers
- Error Handling
- Fail-over (_no assessment yet which is better for the 2 provides in terms of cost_)
### Add-Ons
- A README.md on the overview and general usage
- Trace Header

###  OPTIONAL
- Swagger Documentation
- An ARCHITECTURE.md with technical decisions
- API Metrics | ELK Stack
- API Resiliency | Horizontal Scaling
- Security | JWT / Oauth2
- Logging | Logback
- Deploying your solution somewhere (URL) for us to play with it. | Heroku or Redhat Sandbox

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
  make run
```
Test using the postman collection
```bash
  make runPostman
```

## Demo

Insert gif or link to demo
