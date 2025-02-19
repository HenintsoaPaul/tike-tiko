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
    nom_client               VARCHAR(100),
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
    nom_client          VARCHAR(100),
    img_passeport       VARCHAR(100),
    heure_reservation   TIMESTAMP NOT NULL
);

-- views

create or replace view v_vol as
select v.*,
       vld.nom    as nom_ville_depart,
       vldest.nom as nom_ville_destination
from vol v
         left join ville vld on v.id_ville_depart = vld.id
         left join ville vldest on v.id_ville_destination = vldest.id;

create or replace view v_pourcentage_promotion as
select pp.*, ts.nom as nom_type_siege
from pourcentage_promotion pp
         join type_siege ts on pp.id_type_siege = ts.id;

create or replace view v_reservation as
select r.id,
       r.heure_reservation,
       v.id   as id_vol,
       ts.nom as nom_type_siege,
       er.nom as nom_etat_reservation,
       pv.prix_avec_promo,
       pv.prix_sans_promo,
       pv.is_promotion,
       pp.val as val_promo
from reservation r
         join etat_reservation er on r.id_etat_reservation = er.id
         join place_vol pv on r.id_place_vol = pv.id
         join type_siege ts on pv.id_type_siege = ts.id
         join vol v on pv.id_vol = v.id
         join pourcentage_promotion pp on pv.id_pourcentage_promotion = pp.id;


-- data

INSERT INTO ville (nom)
VALUES ('Paris'),
       ('New York'),
       ('Tokyo'),
       ('Dubai'),
       ('Londres');

INSERT INTO type_siege (nom)
VALUES ('Business'),
       ('Économique');

INSERT INTO utilisateur (nom, password)
VALUES ('admin', 'admin123'),
       ('user1', 'password1'),
       ('user2', 'password2');

INSERT INTO pourcentage_promotion (id_type_siege, val, date_modification)
VALUES (1, 20, NOW()),
       (2, 10, NOW());

INSERT INTO min_nb_heure_reservation (val, date_modification)
VALUES (3, NOW());

INSERT INTO min_nb_heure_annulation (val, date_modification)
VALUES (5, NOW());

INSERT INTO avion (modele, siege_business, siege_eco, date_fabrication)
VALUES ('Boeing 747', 50, 200, '2015-06-15'),
       ('Airbus A380', 70, 300, '2018-09-21');

INSERT INTO vol (id_avion, id_ville_depart, id_ville_destination, heure_depart, heure_arrivee, prix_place_business,
                 prix_place_eco, nb_place_promo_business, nb_place_promo_eco)
VALUES (1, 1, 2, '2025-03-15 09:15:00', '2025-03-15 14:30:00', 1500, 700, 5, 10),
       (2, 3, 4, '2025-04-10 16:45:00', '2025-04-10 23:00:00', 1800, 850, 7, 12);

INSERT INTO place_vol (id_vol, id_type_siege, id_pourcentage_promotion, prix_sans_promo, prix_avec_promo, is_promotion)
VALUES (1, 1, 1, 1500, 1200, TRUE),
       (1, 2, 2, 700, 630, TRUE),
       (2, 1, 1, 1800, 1440, TRUE),
       (2, 2, 2, 850, 765, TRUE);

INSERT INTO etat_reservation (nom)
VALUES ('Confirmée'),
       ('Annulée (client)'),
       ('En attente'),
       ('Annulée (admin)');

INSERT INTO reservation (id_etat_reservation, id_place_vol, heure_reservation)
VALUES (1, 1, '2025-03-10 12:00:00'),
       (2, 3, '2025-04-08 08:30:00');

