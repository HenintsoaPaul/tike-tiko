package entity;

import src.summer.annotations.form.validation.Required;

public class Utilisateur {
    @Required
    int id;

    @Required
    String nom;

    @Required
    String email;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    // Getters n Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
