FROM openjdk:11

WORKDIR /challenge

COPY challengeApp.jar /challenge/challengeApp.jar

EXPOSE 8089

ENTRYPOINT exec java \
    -Dfile.encoding=UTF-8 \
    -jar /challenge/challengeApp.jar