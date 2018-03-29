FROM java:8
VOLUME /tmp
EXPOSE 8090
ADD target/workouttracker-0.0.1-SNAPSHOT.jar workouttracker-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","workouttracker-0.0.1-SNAPSHOT.jar"]