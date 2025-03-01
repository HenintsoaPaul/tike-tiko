# Tike-Tiko

Application de reservation de vol, utilisant le framework `__summer__`.

## Specifications

* Java 8
* PostgreSQL 15.3 (peut etre lancee depuis un container Docker)
* __summer__ MVC framework

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

## Features

### BackOffice

#### Configurations

* **Gestion des configurations**: page permettant de visualiser l'historique de modification
des configurations(promotion, limite reservation, limite annulation) et permettant d'ajouter
de nouvelles valeurs pour ces configurations.

#### Vols

* **Liste**: liste des vols avec filtre multi-critere
* **Ajouter vol**: formulaire d'insertion d'un nouveau vol
* **Fiche**: fiche descriptif d'un vol specifique

### FrontOffice

#### Reservations

* **Mes reservations**: liste des reservations associe au client connecte
* **Ajouter resevation**: ajoute une reservation sur un vol specifique si la date de
reservation limite n'est pas encore depassee.
* **Annulation reservation**: permet d'annuler une reservation sur un vol si la date d'
annulation limite n'est pas encore depassee.
* **Validation reservation**: permet de valider une reservation en envoyer le passeport
du client depuis un upload fichier.

#### Vols

* **Liste**: liste des vols avec filtre multi-criteres
* **Fiche**: affiche les details d'un vol