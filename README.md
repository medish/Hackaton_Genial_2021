# Hackaton_Genial_2021
Documentation du Projet 

Backend Installation du Serveur Spring
Les dépendances Maven sont situées dans le fichier Hackaton_Genial_2021\backend\pom.xml

Prérequis :
- PostgreSQL installé
- Créer une Database dans PostgreSQL (ex: Mydatabase)
- Java 16 installé avec la variable Java_Home initialisée

Configurer les fichiers suivants avec vos identifiants PostgreSQL
Hackaton_Genial_2021\backend\src\main\resources\application.properties
Hackaton_Genial_2021\backend\src\main\resources\hibernate.cfg.xml

Plus particulièrement les champs url, username et password.
comme dans l'exemple suivant :

spring.datasource.url=jdbc:postgresql://localhost/app_calendar
spring.datasource.username=postgres
spring.datasource.password=root


Exécuter le serveur avec le main situé dans :
Hackaton_Genial_2021\backend\src\main\java\server\MainServer.java

Frontend Installation du projet Angular

Prérequis :
  -Node.js installé : node version 14.17, npm version 6.14
  
Dans le dossier front/angular-ui faire :
`npm i` puis `npm install -g @angular/cli`

Pour lancer le projet angular :
`ng serve`

Pour lancer le mockserver :
depuis Hackaton_Genial_2021\front\mockserver lancer :
`npm install -g json-server` puis `json-server test.json --routes routes.json`
