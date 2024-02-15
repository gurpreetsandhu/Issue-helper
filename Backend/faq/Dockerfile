From openjdk:11-jre
RUN mkdir usr/faqservice
WORKDIR usr/faqservice
ADD ./target/faq-0.0.1-SNAPSHOT.jar faq-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "faq-0.0.1-SNAPSHOT.jar"]