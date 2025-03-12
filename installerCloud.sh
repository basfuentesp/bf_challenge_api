#Genenar jar del proyecto en carpeta target
mvn clean install

#Cambiar permisos a jar para ejecucion
chmod +x target/challengeApp.jar 

#Copiar archivo a directorio actual de build
cp target/challengeApp.jar .

#Construir y subir imagen Docker a GCP
gcloud builds submit . --tag us.grc.io/challenge/bf_challenge_api:latest

#Eliminar jar utilizado
rm challengeApp.jar