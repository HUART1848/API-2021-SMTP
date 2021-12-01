# coolsmtp

### Rapport API-2021-SMTP par Alice Grunder et Hugo Huart

## 1. Introduction

Ce programme blablabla

## 2. Serveur SMTP "dockerisé"

Pour utiliser "MockMock" comme un serveur SMTP mock, un fichier Dockerfile est disponible dans le dossier mockmock.

Pour l'utiliser:

1. Se déplacer dans le dossier 'mockmock' et construire l'image avec `docker build -t mockmock .`
2. Lancer l'image et le serveur sur le port 8282 avec `docker run -d -p 8282:8282 mockmock`
