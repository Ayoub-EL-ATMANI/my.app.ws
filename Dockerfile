FROM openjdk:11
ADD target/my.app.ws-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java","-jar", "/app.war"]