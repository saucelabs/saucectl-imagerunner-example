FROM maven:3-jdk-11-slim

ENV LANG=en_US.UTF-8

WORKDIR /usr/build
COPY    pom.xml       .
COPY    best-practice .

RUN mvn de.qaware.maven:go-offline-maven-plugin:resolve-dependencies
