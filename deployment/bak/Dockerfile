FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD app.jar app.jar
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
RUN echo "JAVA_OPTS: $JAVA_OPTS"
ENV TZ=Europe/Berlin
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
