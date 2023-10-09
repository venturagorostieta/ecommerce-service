FROM openjdk:8-jdk-alpine

LABEL maintainer="ventura.gorostieta@company.com"

WORKDIR /app

COPY target/tech-trove-ecommerce--0.0.1-SNAPSHOT.jar /app/tech-trove-ecommerce--0.0.1-SNAPSHOT.jar

EXPOSE 9208

CMD ["java", "-jar", "/app/tech-trove-ecommerce.jar"]
