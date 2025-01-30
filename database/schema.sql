-- Table pour stocker les informations sur les avions
CREATE TABLE avion (
    id SERIAL PRIMARY KEY,
    modele VARCHAR(100) NOT NULL,
    siege_business INT NOT NULL,
    siege_eco INT NOT NULL,
    date_fabrication DATE NOT NULL
);

-- Table pour stocker les informations sur les villes
CREATE TABLE ville (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

-- Table pour stocker les informations sur les utilisateurs
CREATE TABLE utilisateur (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

-- Table pour stocker les informations sur les vols
CREATE TABLE vol (
    id SERIAL PRIMARY KEY,
    avion_id INT REFERENCES avion(id) ON DELETE CASCADE,
    ville_depart_id INT REFERENCES ville(id) ON DELETE CASCADE,
    ville_arrivee_id INT REFERENCES ville(id) ON DELETE CASCADE,
    date_depart TIMESTAMP NOT NULL,
    date_arrivee TIMESTAMP NOT NULL
);

-- Table pour stocker les réservations
CREATE TABLE reservation (
    id SERIAL PRIMARY KEY,
    utilisateur_id INT REFERENCES utilisateur(id) ON DELETE CASCADE,
    vol_id INT REFERENCES vol(id) ON DELETE CASCADE,
    type_siege VARCHAR(50) NOT NULL CHECK (type_siege IN ('Business', 'Eco')), -- Pas besoin d'une table séparée
    date_reservation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    statut VARCHAR(50) DEFAULT 'confirmé'
);

-- Table pour stocker les promotions par vol
CREATE TABLE promotion (
    id SERIAL PRIMARY KEY,
    vol_id INT REFERENCES vol(id) ON DELETE CASCADE,
    type_siege VARCHAR(50) NOT NULL CHECK (type_siege IN ('Business', 'Eco')),
    pourcentage_promotion INT NOT NULL,
    date_debut TIMESTAMP NOT NULL,
    date_fin TIMESTAMP NOT NULL
);

