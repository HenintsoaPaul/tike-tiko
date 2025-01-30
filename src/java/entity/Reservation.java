package entity;

import src.summer.annotations.form.validation.Min;
import src.summer.annotations.form.validation.Required;

import java.time.LocalDate;

public class Reservation {
    @Required
    int id;

    @Required
    int utilisateur_id;

    @Required
    @Min(1)
    int vol_id;

    @Required
    @Min(1)
    int type_siege;

    @Required
    LocalDate date_reservation;

    @Required
    @Min(1)
    String statut;

    // Constr
    public Reservation() {
    }

    // Getters n Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = Reservation.this.utilisateur_id;
    }

    public int getVol_id() {
        return vol_id;
    }

    public void setVol_id(int vol_id) {
        this.vol_id = vol_id;
    }

    public int getType_siege() {
        return type_siege;
    }

    public void setType_siege(int type_siege) {
        this.type_siege = type_siege;
    }

    public LocalDate getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(LocalDate date_reservation) {
        this.date_reservation = date_reservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
