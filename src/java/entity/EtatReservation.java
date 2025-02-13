package entity;

import src.summer.annotations.form.validation.Required;

public class EtatReservation {
    @Required
    int id;

    @Required
    String nom;

    // Constr
    public EtatReservation() {
    }

    // Getters n Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
