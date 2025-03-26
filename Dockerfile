#Imagen publica openjdk
FROM openjdk:17

#Directorio base donde estara el proyecto jar
WORKDIR /challenge

# Copiar el archivo JAR en contenedor
COPY challengeApp.jar /challenge/challengeApp.jar

#Puerto de exposicion del servicio
EXPOSE 8089

#Variable de entorno passBD
ENV PASSBD=

#Comandos que se ejecutan al momento de que la imagen se levanta
ENTRYPOINT exec java \
    -Dfile.encoding=UTF-8 \
	-Duser.timezone=America/Santiago \
    -jar /challenge/challengeApp.jar
