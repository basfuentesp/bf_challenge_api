#Genenar jar del proyecto en carpeta target
mvn clean install

#Cambiar permisos a jar para ejecucion
chmod +x target/challengeApp.jar 

#Copiar archivo a directorio actual de build (dentro del contenedor)
cp target/challengeApp.jar .

#Construir imagen Docker EN LOCAL
docker build -t challengeapp:v1 .

#Eliminar jar utilizado
rm challengeApp.jar