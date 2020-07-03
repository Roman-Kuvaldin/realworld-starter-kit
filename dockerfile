FROM openjdk:11
COPY ./vertx-mongodb/target/vertx-mongodb-1.0.0-SNAPSHOT-fat.jar /opt/apps/vertx-mongodb/vertx-mongodb.jar
ENV APP_PORT=8080
EXPOSE $APP_PORT
# Running app
ENTRYPOINT ["java", "-jar", "/opt/apps/vertx-mongodb/vertx-mongodb.jar"]
