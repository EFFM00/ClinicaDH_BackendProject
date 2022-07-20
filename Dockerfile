FROM openjdk:18

ARG JAR_FILE=target/s.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8080