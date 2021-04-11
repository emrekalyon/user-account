FROM adoptopenjdk/openjdk8
MAINTAINER Emre Kalyoncu <emrekalyon@yahoo.com>

ARG JVM_OPTS='-Xmx4g'
VOLUME /var/log
COPY target/app.jar /app.jar

EXPOSE 8082

CMD java $JVM_OPTS -Dnetworkaddress.cache.ttl=60 -jar /app.jar
