# Hackaton_Genial_2021
Documentation du Projet 
Backend Installation du Serveur Spring
Les dépendances Maven sont situées dans le fichier **Hackaton_Genial_2021\backend\pom.xml**

# Prérequis côté backend :
- PostgreSQL installé
- Créer une Database dans PostgreSQL (ex: app_calendar)
- Java 16 installé avec la variable Java_Home initialisée

 ## instructions d'installation
Configurer les fichiers suivants avec vos identifiants PostgreSQL: <br>
**Hackaton_Genial_2021\backend\src\main\resources\application.properties** <br>
**Hackaton_Genial_2021\backend\src\main\resources\hibernate.cfg.xml**

Plus particulièrement les champs url, username et password.
comme dans l'exemple suivant :
**spring.datasource.url=jdbc:postgresql://localhost/app_calendar** <br>
**spring.datasource.username=postgres**<br>
**spring.datasource.password=root**<br>


Exécuter le serveur avec le main situé dans : <br>
**Hackaton_Genial_2021\backend\src\main\java\server\MainServer.java**
ou depuis le dossier backend en executant la commande : mvn clean compile exec:java


Une fois le serveur lancé, une documentation générée par Swagger est disponible à l'adresse suivante : 
http://localhost:8080/swagger-ui.html

# Prérequis côté front :

Frontend Installation du projet Angular

Prérequis :
  - Node.js installé : node version 14.17, npm version 6.14

 ## instructions d'installation
Dans le dossier front/angular-ui faire :
`npm i` puis `npm install -g @angular/cli`

Pour lancer le projet angular :
`ng serve`

Une fois le backend et le frontend lancés, l'application est disponible sur le lien suivant : 
http://localhost:4200/login

