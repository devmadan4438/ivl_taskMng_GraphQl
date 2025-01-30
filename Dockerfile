FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/TaskMngGraphQl-0.0.1-SNAPSHOT.jar /app/TaskMngGraphQl-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "TaskMngGraphQl-0.0.1-SNAPSHOT.jar"]