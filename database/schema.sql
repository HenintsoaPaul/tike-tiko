CREATE TABLE ville
(
    id  SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

CREATE TABLE type_siege
(
    id  SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

CREATE TABLE utilisateur
(
    id       SERIAL PRIMARY KEY,
    nom      VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE pourcentage_promotion
(
    id                SERIAL PRIMARY KEY,
    id_type_siege     INT REFERENCES type_siege (id),
    val               NUMERIC   NOT NULL,
    date_modification TIMESTAMP NOT NULL
);

CREATE TABLE min_nb_heure_reservation
(
    id                SERIAL PRIMARY KEY,
    val               NUMERIC   NOT NULL,
    date_modification TIMESTAMP NOT NULL
);

CREATE TABLE min_nb_heure_annulation
(
    id                SERIAL PRIMARY KEY,
    val               NUMERIC   NOT NULL,
    date_modification TIMESTAMP NOT NULL
);

CREATE TABLE avion
(
    id               SERIAL PRIMARY KEY,
    modele           VARCHAR(100) NOT NULL,
    siege_business   INT          NOT NULL,
    siege_eco        INT          NOT NULL,
    date_fabrication DATE         NOT NULL
);

CREATE TABLE vol
(
    id                      SERIAL PRIMARY KEY,
    id_avion                INT REFERENCES avion (id),
    id_ville_depart         INT REFERENCES ville (id),
    id_ville_destination    INT REFERENCES ville (id),
    heure_depart            TIMESTAMP NOT NULL,
    heure_arrivee           TIMESTAMP NOT NULL,
    prix_place_business     NUMERIC   NOT NULL,
    prix_place_eco          NUMERIC   NOT NULL,
    nb_place_promo_business INT       NOT NULL,
    nb_place_promo_eco      INT       NOT NULL
);

CREATE TABLE place_vol
(
    id                       SERIAL PRIMARY KEY,
    id_vol                   INT REFERENCES vol (id),
    id_type_siege            INT REFERENCES type_siege (id),
    id_pourcentage_promotion INT REFERENCES pourcentage_promotion (id),
    prix_sans_promo          numeric NOT NULL,
    prix_avec_promo          numeric,
    is_promotion             BOOLEAN NOT NULL
);

CREATE TABLE etat_reservation
(
    id  SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

CREATE TABLE reservation
(
    id                  SERIAL PRIMARY KEY,
    id_etat_reservation INT REFERENCES etat_reservation (id),
    id_place_vol        INT REFERENCES place_vol (id),
    heure_reservation   TIMESTAMP NOT NULL
);


/*
1 vol
    -> 1,1 avion
    -> n places business
        + prix place business
            + promo business
            + % promo
    -> n places eco
        + prix place eco
            + promo eco
            + % promo
*/

/*
MIN NB HEURE AVANT RESERVATION:
Si on a un vol qui part pour 9h15,
    si min_nb_heure_reservation = 3
    -> A 6h15, no afaka misy afaka mandefa reservation farany ho an'io vol io
*/

/*
MIN NB HEURE AVANT ANNULATION:
Si on a un vol qui part pour 9h15,
    si min_nb_heure_annulation = 5
    -> A 4h15, no afaka afaka mandefa annulation farany ho an'io vol io
*/

create or replace view v_pourcentage_promotion as
select pp.*, ts.nom as nom_type_siege
from pourcentage_promotion pp
         join type_siege ts on pp.id_type_siege = ts.id;