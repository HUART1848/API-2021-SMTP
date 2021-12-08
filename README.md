# coolsmtp

### Rapport API-2021-SMTP par Alice (Yannick) Grunder et Hugo Huart

## 1. Introduction

Ce programme envoie des e-mails "prank" forgés. Ceci est fait avec l'API Socket en créant un client SMTP.

Ce programme permet de voir à quel point il est facile de faire de faux e-mails.

On peut utiliser "MockMock" pour simuler une utilisation du programme.

## 2. Qu'est-ce que c'est "MockMock" ?

"[MockMock](https://github.com/HEIGVD-Course-API/MockMock)" est un programme java qui permet de mettre en place un
serveur SMTP affin de pouvoir tester des programmes envoyant des e-mails sans vraiment en envoyer sur le net.

Cela est très utile pour plusieurs raisons :

1. Puisque le serveur est sur la même machine la reception d'e-mail est quasi instantané.
2. On n'envoie pas vraiment d'e-mail, cela évite d'embêter de vraies personnes lors de test.
3. On peut voir les e-mails apres les avoir envoyé et verifier que le programme fonctionne comme prévu.

La page github de "MockMock" explique comment le lancer

## 3. Serveur SMTP "dockerisé"

Le serveur "MockMock" est directement fourni dans ce repo, dans le dossier `mockmock`.

Pour l'utiliser directement comme un serveur SMTP mock tournant dans un conteneur Docker, un fichier `Dockerfile` est
disponible dans le dossier `mockmock` afin de construire l'image correspondante.

Pour créer et utiliser le conteneur (Si besoin, recompiler "MockMock" auparavant) :

1. Se déplacer dans le dossier `mockmock` et construire l'image avec `docker build -t mockmock .`
2. Lancer le conteneur et le serveur sur le port 2525 pour SMTP et 8282 pour l'interface web
   avec `docker run -d -p 2525:2525 -p 8282:8282 mockmock`

## 4. Comment utiliser coolsmtp

Le programme prend une liste d'adresses e-mail et la sépare en un nombre de groupes prédéterminé. Pour chaque groupe, le
programme envoie un message pris au hasard dans une liste donnée en utilisant la première adresse du groupe comme
expéditeur du message et le reste comme destinataires du message.

Il y a 3 fichiers importants à modifier dans le dossier **"config"** :

1. **"config.txt"** : Ce fichier contient l'adresse (1ère ligne) et le port du serveur (2e ligne) ainsi que le nombre de
   groupes attendu (3e ligne) :

```
[adresse]
[port]
[nombre de groupes]
```

2. **"messages.txt"** : Ce fichier contient la liste des messages possibles. Les messages différents sont séparés par "
   ===" et doivent tous se conformer au format suivant :

```
Subject: Lorem ipsum

Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
```

3. **"users.txt"** : Contient la liste des adresses e-mail des victimes, avec une adresse par ligne. Une adresse a le
   format suivant `lorem@ipsum.ut`.
   _Attention il faut assez d'adresses e-mail pour avoir au moins 3 personnes par groupes._

Une fois les fichiers de configuration modifiés comme voulu, il faut lancer la compilation du
paquet à l'aide de Maven depuis la racine du projet. Puis, toujours depuis la racine du projet, lancer la
commande `java -jar target/coolsmtp-1.0-SNAPSHOT.jar`. Il faut faire bien attention à ce que la commande`java -jar` soit
effectuée dans le répertoire où se situe le dossier `config`.

Une fois l'application démarrée, les emails seront envoyés, moyennant des fichiers de configuration sans erreurs.

## 5. Implémentation

La classe **Group** représente l'adresse e-mail de l'expéditeur et les adresses des victimes. Elle implémente une méthode
qui renvoie un tableau de `Group` à partir de `config/users.txt` en vérifiant qu'il n'y ait pas d'erreur.

La classe **Message** représente les messages que l'on peut envoyer. Elle implémente une méthode qui permet d'avoir un
message au hasard depuis la liste dans `config/messages.txt`

La classe **Parser** implémente les méthodes pour obtenir le texte entier des fichiers de config ainsi que leurs
versions ligne par ligne sous forme de tableau de String.

La classe **Config** permet d'obtenir et de représenter les informations contenues dans `config/config.txt`

La classe **SmtpClient** est le client SMTP qui envoie un message au serveur en utilisant l'APi Socket. Elle est
responsable de la connexion au serveur et de l'envoi de messages en suivant le protocole SMTP.

La classe **App** contient la fonction `main`.
