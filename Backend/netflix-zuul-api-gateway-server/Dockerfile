From openjdk:11-jre
RUN mkdir usr/gateway
WORKDIR usr/gateway
ADD ./target/netflix-zuul-api-gateway-server-0.0.1-SNAPSHOT.jar netflix-zuul-api-gateway-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "netflix-zuul-api-gateway-server-0.0.1-SNAPSHOT.jar"]