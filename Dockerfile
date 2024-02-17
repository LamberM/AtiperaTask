FROM maven:3.9.5 AS build
WORKDIR /home/java/atipera
COPY src /home/java/atipera/src
COPY pom.xml /home/java/atipera
RUN mvn -f /home/java/atipera/pom.xml clean package

FROM openjdk:21-jdk-slim
WORKDIR /home/java/atipera
COPY --from=build /home/java/atipera/target/AtiperaTask-0.0.1-SNAPSHOT.jar /home/atiperaTask.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/atiperaTask.jar"]