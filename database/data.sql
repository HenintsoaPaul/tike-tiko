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
       ('Annulée'),
       ('En attente');

INSERT INTO reservation (id_etat_reservation, id_place_vol, heure_reservation)
VALUES (1, 1, '2025-03-10 12:00:00'),
       (2, 3, '2025-04-08 08:30:00');

