# Tike-Tiko

Application de reservation de vol, utilisant le framework `__summer__`.

## Specifications

* Java 8
* PostgreSQL 15.3
* __summer__

### Architecture

* `./build-file/`: contient les librairies necessaires ainsi que les fichiers de build
* `./src/`: contient les fichiers de l'application java a compiler (.java)
* `./web/`: contient les fichiers de l'application web (.jsp)
* `./web/WEB-INF/web.xml`: fichier de configuration de l'application web
* `./build.xml`: fichier de build

## Lancement du projet

Executer la commande suivante dans le terminal:

```bash
  ant build.xml
```