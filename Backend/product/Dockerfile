From openjdk:11-jre
RUN mkdir usr/productservice
WORKDIR usr/productservice
ADD ./target/product-0.0.1-SNAPSHOT.jar product-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "product-0.0.1-SNAPSHOT.jar"]