package entity;

import src.summer.annotations.form.validation.Required;

public class TypeSiege {
    @Required
    int id;

    @Required
    String nom;

    // Constr
    public TypeSiege() {
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
