package entity;

import src.summer.annotations.form.validation.Required;

public class TrancheAge {
    @Required
    int id;

    @Required
    String nom;

    @Required
    int age_min;


    @Required
    int age_max;

    // Constr
    public TrancheAge() {
    }

    public TrancheAge(int id, String nom, int ageMin, int ageMax) {
        this.id = id;
        this.nom = nom;
        this.age_min = ageMin;
        this.age_max = ageMax;
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

    public int getAge_min() {
        return age_min;
    }

    public void setAge_min(int age_min) {
        this.age_min = age_min;
    }

    public int getAge_max() {
        return age_max;
    }

    public void setAge_max(int age_max) {
        this.age_max = age_max;
    }
}
