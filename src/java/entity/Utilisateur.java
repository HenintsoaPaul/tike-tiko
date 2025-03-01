package entity;

import src.summer.annotations.form.validation.Required;

public class Utilisateur {

    int id;

    String nom;

    @Required
    String email;

    @Required
    String password;

    int auth_level;

    public Utilisateur() {}

    public Utilisateur(int id, String nom, String password, int authLevel) {
        this.id = id;
        this.nom = nom;
        this.password = password;
        this.auth_level = authLevel;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAuth_level() {
        return auth_level;
    }

    public void setAuth_level(int auth_level) {
        this.auth_level = auth_level;
    }
}
