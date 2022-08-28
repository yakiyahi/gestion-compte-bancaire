FROM openjdk:latest
ADD target/ProjectApiRest-0.0.1-SNAPSHOT.jar gest-bank-api.jar
ENTRYPOINT ["java","-jar","gest-bank-api.jar"]
EXPOSE 8080
