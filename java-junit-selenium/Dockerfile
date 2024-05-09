FROM maven:3.9.6-eclipse-temurin-11

ENV LANG=en_US.UTF-8

WORKDIR /usr/build
COPY    pom.xml       .
COPY    best-practice ./best-practice

RUN mvn de.qaware.maven:go-offline-maven-plugin:resolve-dependencies
