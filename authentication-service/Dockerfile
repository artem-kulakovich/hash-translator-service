FROM openjdk:11
EXPOSE 8081
WORKDIR /authentication/application
COPY ../build/libs/authentication-service-0.0.1-SNAPSHOT.jar /authentication/application
ENTRYPOINT ["java","-jar","authentication-service-0.0.1-SNAPSHOT.jar"]