package entity;

import src.summer.annotations.form.validation.Required;

public class Utilisateur {

    int id;

    @Required
    String nom;

    @Required
    String password;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String password) {
        this.nom = nom;
        this.password = password;
    }

    // Getters n Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
