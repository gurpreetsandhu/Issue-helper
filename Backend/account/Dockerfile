From openjdk:11-jre
RUN mkdir usr/accountservice
WORKDIR usr/accountservice
ADD ./target/account-0.0.1-SNAPSHOT.jar account-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "account-0.0.1-SNAPSHOT.jar"]