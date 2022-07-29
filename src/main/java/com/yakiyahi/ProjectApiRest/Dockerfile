FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/GestBankApi*.jar /gestionbankapi.jar
CMD ["java","-jar","/gestionbankapi.jar","--spring.profiles.active=prod"]
EXPOSE 8082