From openjdk:11-jre
RUN mkdir usr/queryservice
WORKDIR usr/queryservice
ADD ./target/query-0.0.1-SNAPSHOT.jar query-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "query-0.0.1-SNAPSHOT.jar"]