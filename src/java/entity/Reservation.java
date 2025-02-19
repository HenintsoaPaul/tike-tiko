package entity;

import src.summer.annotations.form.validation.Required;

import java.time.LocalDateTime;

public class Reservation {
    @Required
    int id;

    @Required
    int id_etat_reservation;

    @Required
    int id_place_vol;

    @Required
    LocalDateTime heure_reservation;

    String nom_client;

    // Constr
    public Reservation() {
    }

    public Reservation(int id, int id_etat_reservation, int id_place_vol, LocalDateTime heure_reservation, String nom_client) {
        this.id = id;
        this.id_etat_reservation = id_etat_reservation;
        this.id_place_vol = id_place_vol;
        this.heure_reservation = heure_reservation;
        this.nom_client = nom_client;
    }

    // Getters n Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_etat_reservation() {
        return id_etat_reservation;
    }

    public void setId_etat_reservation(int id_etat_reservation) {
        this.id_etat_reservation = id_etat_reservation;
    }

    public int getId_place_vol() {
        return id_place_vol;
    }

    public void setId_place_vol(int id_place_vol) {
        this.id_place_vol = id_place_vol;
    }

    public LocalDateTime getHeure_reservation() {
        return heure_reservation;
    }

    public void setHeure_reservation(LocalDateTime heure_reservation) {
        this.heure_reservation = heure_reservation;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }
}
