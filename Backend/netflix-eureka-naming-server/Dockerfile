From openjdk:11-jre
RUN mkdir usr/namingserver
WORKDIR usr/namingserver
ADD ./target/netflix-eureka-naming-server-0.0.1-SNAPSHOT.jar netflix-eureka-naming-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "netflix-eureka-naming-server-0.0.1-SNAPSHOT.jar"]