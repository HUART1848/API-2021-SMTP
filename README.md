# coolsmtp

### Rapport API-2021-SMTP par Alice (Yannick) Grunder et Hugo Huart

## 1. Introduction

Ce programme envoi un e-mail "prank" forgés.
Ceci est fait avec l'API Socket en créant un client SMTP.

Ce programme permet de voir à quel point il est facile de faire de faux e-mails.

On peut utiliser "MockMock" pour simuler une utilisation du programme.

## 2. Qu'est-ce que c'est "MockMock" ?
"[MockMock](https://github.com/HEIGVD-Course-API/MockMock)" est un programme java qui permet de mettre en place un serveur SMTP affin de pouvoir tester des programmes envoyant des e-mails sans vraiment en envoyer sur le net.

Cela est très utile pour plusieurs raisons :
1. Puisque le serveur est sur la même machine la reception d'e-mail est quasi instantané.
2. On n'envoie pas vraiment d'e-mail, cela évite d'embêter de vraies personnes lors de test.
3. On peut voir les e-mails apres les avoir envoyé et verifier que le programme fonctionne comme prévu.

La page github de "MockMock" explique comment le lancer

## 3. Serveur SMTP "dockerisé"

Pour utiliser "MockMock" comme un serveur SMTP mock, un fichier Dockerfile est disponible dans le dossier mockmock.

Pour l'utiliser :

1. Se déplacer dans le dossier 'mockmock' et construire l'image avec `docker build -t mockmock .`
2. Lancer l'image et le serveur sur le port 8282 pour l'interface web et 2525 pour STMP
   avec `docker run -d -p 2525:2525 -p 8282:8282 mockmock`

## 4. Comment utiliser coolsmtp
Le programme prend une liste d'adresse e-mail et la sépare en un nombre de groupes prédéterminé.
Pour chaque groupe le programme envoie un message pris au hasard dans une liste donnée en utilisant la première adresse du groupe comme envoyeur du message et le reste comme récepteurs du message.

Il y a 3 fichiers important à modifier dans le dossier **"config"** :
1. **"config.txt"** : Ce fichier contient l'adresse (1ère ligne) et le port du serveur (2e ligne) ainsi que le nombre de groupes attendu (3e ligne) :
```
[adresse]
[port]
[nombre de groupes]
```
2. **"messages.txt"** : Ce fichier contient la liste des messages possibles. Les messages différents sont séparés par "===" et doivent tous se conformer au format suivant :
```
Subject: Lorem ipsum

Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
```
3. **"users.txt"** : Contient la liste des adresses e-mail des victimes, avec une adresse par ligne. Une adresse a le format suivant `lorem@ipsum.ut`.
_Attention il faut assez d'adresses e-mail pour avoir au moins 3 personnes par groupes._

Une fois les fichiers de configuration modifiés comme voulu il faut, depuis la racine du projet, lancer la commande `java -jar target/coolsmtp-1.0-SNAPSHOT.jar`
et les emails seront envoyé, moyennant des fichiers de configs sans erreurs.

## 5. Implémentation
La classe **Group** représente l'adresse e-mail de l'envoyeur et les adresses des victimes.
Elle implémente une méthode qui renvoi un tableau de `Group` à partir de `config/users.txt` en vérifiant qu'il n'y ait pas d'erreur.

La classe **Message** représente les messages que l'on peut envoyer.
Elle implémente une méthode qui permet d'avoir un message au hasard depuis la liste dans `config/messages.txt`

La classe **Parser** implémente les méthodes pour obtenir le texte entier des fichiers de config ainsi que leurs versions ligne par ligne sous forme de tableau de String.

La classe **Config** permet d'obtenir les informations contenues dans `config/config.txt`
