# OpenJDK
FROM openjdk:20-jdk-slim AS builder

# Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean

# pom.xml
COPY pom.xml /usr/src/app/
COPY src /usr/src/app/src/

WORKDIR /usr/src/app

# Resolve dependencies
RUN mvn -f /usr/src/app/pom.xml dependency:resolve

RUN mvn -v 
RUN mvn -N io.takari:maven:wrapper -Dmaven=3.8.4  

# Compile project
RUN mvn clean install

EXPOSE 9092

CMD ["java", "-jar", "target/StudentRecords-BackEnd-1.0-SNAPSHOT.jar"]
