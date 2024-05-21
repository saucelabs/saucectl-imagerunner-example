FROM maven:3.9.6-eclipse-temurin-11

ENV LANG=en_US.UTF-8

WORKDIR /usr/build
COPY    pom.xml .
COPY    src ./src

RUN mvn install
