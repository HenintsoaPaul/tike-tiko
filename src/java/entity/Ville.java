package entity;

import src.summer.annotations.form.validation.Required;

public class Ville {
    @Required
    int id;

    @Required
    String nom;

    // Constr
    public Ville() {
    }

    public Ville(int id, String nom) {
        this.id = id;
        this.nom = nom;
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
