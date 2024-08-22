LABEL authors="carlo"
FROM openjdk:17-jdk-slim
VOLUME /tmp
EXPOSE 8080
ADD target/customer-service-0.0.1-SNAPSHOT.jar customer-service.jar
ENTRYPOINT ["java","-jar","/customer-service.jar"]