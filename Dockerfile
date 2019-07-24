FROM java:8
WORKDIR /backend
COPY  ./target/hero-rest-api-0.0.1-SNAPSHOT.jar  server.jar
ENTRYPOINT ["java" , "-jar", "server.jar"]