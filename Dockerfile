FROM java:8
VOLUME /tmp
EXPOSE 8090
ADD target/spring-boot-jpa-0.0.1-SNAPSHOT.jar spring-boot-jpa-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","spring-boot-jpa-0.0.1-SNAPSHOT.jar"]