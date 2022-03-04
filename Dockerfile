# base image
FROM openjdk:11.0-jre-slim

# default to libs folder if no param has been set
ARG JAR_FILE=build/libs/mail-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
# for app
EXPOSE 8080
# for health
EXPOSE 8081
# command to run the application
ENTRYPOINT ["java","-jar","/app.jar"]