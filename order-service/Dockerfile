LABEL authors="carlo"

FROM openjdk:17-jdk-slim
VOLUME /tmp
EXPOSE 8080
ADD target/order-service-0.0.1-SNAPSHOT.jar order-service.jar
ENTRYPOINT ["java","-jar","/order-service.jar"]