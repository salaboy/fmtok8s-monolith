FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
# we can pass now to mvn the jdbc driver like this: -Ddriver.jdbc.group.id=mysql -Ddriver.jdbc.artifact.id=mysql-connector-java


FROM openjdk:11-jre-slim
ENV PORT 8080
ENV CLASSPATH /opt/lib
EXPOSE 8080

# copy app.jar from build step
COPY --from=build /home/app/target/app.jar /target

# copy pom.xml and wildcards to avoid this command failing if there's no target/lib directory
COPY pom.xml target/lib* /opt/lib/

# NOTE we assume there's only 1 jar in the target dir
# but at least this means we don't have to guess the name
# we could do with a better way to know the name - or to always create an app.jar or something
COPY target/app.jar /opt/app.jar
WORKDIR /opt
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar