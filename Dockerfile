FROM adoptopenjdk/openjdk8
COPY target/blog-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar app.jar