FROM openjdk
ARG JAR_FILE=target/mysql-rest-service-0.0.1-SNAPSHOT.jar
WORKDIR .
COPY target/mysql-rest-service-0.0.1-SNAPSHOT.jar .
##RUN SpringDockerSimpleExample-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "mysql-rest-service-0.0.1-SNAPSHOT.jar"]
