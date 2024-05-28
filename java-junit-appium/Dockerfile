FROM maven:3.9.6-eclipse-temurin-11

ENV LANG=en_US.UTF-8

WORKDIR /usr/workspace

COPY apps/ ./apps
COPY src ./src
COPY pom.xml .
COPY upload-apps.sh .

RUN mvn de.qaware.maven:go-offline-maven-plugin:resolve-dependencies
