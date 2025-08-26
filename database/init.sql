CREATE TABLE avion
(
    id               SERIAL,
    modele           VARCHAR(50),
    siege_business   INTEGER NOT NULL,
    siege_eco        INTEGER NOT NULL,
    date_fabrication DATE    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ville
(
    id  SERIAL,
    nom VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE min_nb_heure_reservation
(
    id                SERIAL,
    val               NUMERIC(15, 2) NOT NULL,
    date_modification TIMESTAMP      NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE min_nb_heure_annulation
(
    id                SERIAL,
    val               NUMERIC(15, 2) NOT NULL,
    date_modification TIMESTAMP      NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE type_siege
(
    id  SERIAL,
    nom VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE vol
(
    id                  SERIAL,
    heure_depart        TIMESTAMP      NOT NULL,
    heure_arrivee       TIMESTAMP      NOT NULL,
    prix_place_business NUMERIC(15, 2) NOT NULL,
    prix_place_eco      NUMERIC(15, 2) NOT NULL,
    id_avion            INTEGER        NOT NULL,
    id_ville_arrivee    INTEGER        NOT NULL,
    id_ville_depart     INTEGER        NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_avion) REFERENCES avion (id),
    FOREIGN KEY (id_ville_arrivee) REFERENCES ville (id),
    FOREIGN KEY (id_ville_depart) REFERENCES ville (id)
);

CREATE TABLE promotion
(
    id            SERIAL,
    nb_place      INTEGER        NOT NULL,
    prix_promo    NUMERIC(15, 2) NOT NULL,
    date_fin      DATE           NOT NULL,
    id_vol        INTEGER        NOT NULL,
    id_type_siege INTEGER        NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_vol) REFERENCES vol (id),
    FOREIGN KEY (id_type_siege) REFERENCES type_siege (id)
);

CREATE TABLE utilisateur
(
    id         SERIAL,
    password   VARCHAR(50) NOT NULL,
    nom        VARCHAR(50) NOT NULL,
    email      VARCHAR(50) NOT NULL,
    auth_level INTEGER default 0,
    PRIMARY KEY (id)
);

CREATE TABLE place_vol
(
    id            SERIAL,
    reference     VARCHAR(50),
    id_type_siege INTEGER NOT NULL,
    id_vol        INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_type_siege) REFERENCES type_siege (id),
    FOREIGN KEY (id_vol) REFERENCES vol (id)
);

CREATE TABLE etat_reservation
(
    id  SERIAL,
    nom VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tranche_age
(
    id      SERIAL,
    nom     VARCHAR(50) NOT NULL,
    age_min INTEGER default 0,
    age_max INTEGER default 500,
    PRIMARY KEY (id)
);

CREATE TABLE reduction_tranche_age
(
    id                SERIAL,
    val_pourcentage   NUMERIC(15, 2) NOT NULL,
    date_modification TIMESTAMP      NOT NULL,
    id_tranche_age    INTEGER        NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_tranche_age) REFERENCES tranche_age (id)
);

CREATE TABLE reservation
(
    id                       SERIAL,
    img_passeport            TEXT,
    date_reglement           TIMESTAMP,
    heure_reservation        TIMESTAMP      NOT NULL,
    prix_final               NUMERIC(15, 2) NOT NULL,
    id_promotion             INTEGER,
    id_reduction_tranche_age INTEGER        NOT NULL,
    id_etat_reservation      INTEGER        NOT NULL,
    id_utilisateur           INTEGER        NOT NULL,
    id_place_vol             INTEGER        NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_promotion) REFERENCES promotion (id),
    FOREIGN KEY (id_reduction_tranche_age) REFERENCES reduction_tranche_age (id),
    FOREIGN KEY (id_etat_reservation) REFERENCES etat_reservation (id),
    FOREIGN KEY (id_utilisateur) REFERENCES utilisateur (id),
    FOREIGN KEY (id_place_vol) REFERENCES place_vol (id)
);


-- views

create or replace view v_vol as
select v.*,
       a.siege_business as nb_place_business,
       a.siege_eco      as nb_place_eco,
       vld.nom          as nom_ville_depart,
       vldest.nom       as nom_ville_arrivee
from vol v
         left join avion a on v.id_avion = a.id
         left join ville vld on v.id_ville_depart = vld.id
         left join ville vldest on v.id_ville_arrivee = vldest.id;

create or replace view v_promotion as
select p.*,
       ts.nom as nom_type_siege
from promotion p
         join type_siege ts on p.id_type_siege = ts.id;

create or replace view v_reduction_tranche_age as
select rta.*,
       ta.nom as nom_tranche_age,
       ta.age_min,
       ta.age_max
from reduction_tranche_age rta
         join tranche_age ta on rta.id_tranche_age = ta.id;

create or replace view v_reservation as
select r.id,
       r.heure_reservation,
       r.prix_final,
       r.id_utilisateur,
       u.nom  as nom_utilisateur,
       r.id_promotion,
       r.img_passeport,
       v.id   as id_vol,
       ts.nom as nom_type_siege,
       er.nom as nom_etat_reservation
from reservation r
         join utilisateur u on r.id_utilisateur = u.id
         join etat_reservation er on r.id_etat_reservation = er.id
         join place_vol pv on r.id_place_vol = pv.id
         join type_siege ts on pv.id_type_siege = ts.id
         join vol v on pv.id_vol = v.id;

create or replace view v_curr_reduction_tranche_age as
select rta.*
from reduction_tranche_age rta
where rta.id = (select max(sub_rta.id)
                from reduction_tranche_age sub_rta
                where sub_rta.id_tranche_age = rta.id_tranche_age);


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

INSERT INTO etat_reservation (nom)
VALUES ('Confirmée'),
       ('Annulée (client)'),
       ('En attente'),
       ('Annulée (admin)');

INSERT INTO min_nb_heure_reservation (val, date_modification)
VALUES (3, NOW());

INSERT INTO min_nb_heure_annulation (val, date_modification)
VALUES (5, NOW());

INSERT INTO utilisateur (nom, password, email, auth_level)
VALUES ('rakoto', 'rakoto123', 'rakoto@gmail.com', 10),
       ('rabe', 'rabe123', 'rabe@gmail.com', 5),
       ('client3', 'client3', 'client3@gmail.com', 0),
       ('client2', 'client2', 'client2@gmail.com', 0),
       ('client1', 'client1', 'client1@gmail.com', 0);

INSERT INTO avion (modele, siege_business, siege_eco, date_fabrication)
VALUES ('Boeing 747', 10, 20, '2015-06-15'),
       ('Airbus A380', 15, 20, '2018-09-21');

INSERT INTO tranche_age (nom, age_min, age_max)
VALUES ('Maternelle', 2, 5),
       ('Enfant', 6, 15),
       ('Adulte', 16, 500);

INSERT INTO reduction_tranche_age (val_pourcentage, date_modification, id_tranche_age)
VALUES (20, now(), 1),
       (10, now(), 2),
       (0, now(), 3);

-- INSERT INTO vol (id_avion, id_ville_depart, id_ville_arrivee, heure_depart, heure_arrivee, prix_place_business,
--                  prix_place_eco, nb_place_promo_business, nb_place_promo_eco)
-- VALUES (1, 1, 2, '2025-03-15 09:15:00', '2025-03-15 14:30:00', 1500, 700, 5, 10),
--        (2, 3, 4, '2025-04-10 16:45:00', '2025-04-10 23:00:00', 1800, 850, 7, 12);

-- INSERT INTO place_vol (id_vol, id_type_siege, id_pourcentage_promotion, prix_sans_promo, prix_avec_promo, is_promotion)
-- VALUES (1, 1, 1, 1500, 1200, TRUE),
--        (1, 2, 2, 700, 630, TRUE),
--        (2, 1, 1, 1800, 1440, TRUE),
--        (2, 2, 2, 850, 765, TRUE);

-- INSERT INTO reservation (id_etat_reservation, id_place_vol, heure_reservation)
-- VALUES (1, 1, '2025-03-10 12:00:00'),
--        (2, 3, '2025-04-08 08:30:00');

