########build stage########
FROM maven:3.5-jdk-8 as maven_build
WORKDIR /app

COPY pom.xml .
# To resolve dependencies in a safe way (no re-download when the source code changes)
RUN mvn clean package -Dmaven.main.skip -Dmaven.test.skip && rm -r target

# To package the application
COPY src ./src
RUN mvn clean package -Dmaven.test.skip

########run stage########
FROM adoptopenjdk/openjdk8
MAINTAINER Emre Kalyoncu <emrekalyon@yahoo.com>

ARG JVM_OPTS='-Xmx4g'
VOLUME /var/log
COPY target/app.jar /app.jar

EXPOSE 8082

CMD java $JVM_OPTS -Dnetworkaddress.cache.ttl=60 -jar /app.jar
