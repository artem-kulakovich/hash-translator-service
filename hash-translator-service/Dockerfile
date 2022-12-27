FROM openjdk:11
EXPOSE 8082
WORKDIR /hash-translator/application
COPY ../build/libs/hash-translator-service-0.0.1-SNAPSHOT.jar /hash-translator/application
ENTRYPOINT ["java","-jar","hash-translator-service-0.0.1-SNAPSHOT.jar"]