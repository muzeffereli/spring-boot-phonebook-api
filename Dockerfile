FROM openjdk:11
EXPOSE 8080
ADD target/spring-boot-phonebook-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]