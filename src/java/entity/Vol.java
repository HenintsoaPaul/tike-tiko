package entity;

import src.summer.annotations.form.validation.Min;
import src.summer.annotations.form.validation.Required;

import java.time.LocalDate;

public class Vol {
    @Required
    int id;

    @Required
    int avion_id;

    @Required
    @Min(1)
    int ville_depart_id;

    @Required
    @Min(1)
    int ville_arrivee_id;

    @Required
    LocalDate date_depart;

    @Required
    LocalDate date_arrivee;

    // Constr
    public Vol() {
    }

    // Getters n Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvion_id() {
        return avion_id;
    }

    public void setAvion_id(int avion_id) {
        this.avion_id = Vol.this.avion_id;
    }

    public int getVille_depart_id() {
        return ville_depart_id;
    }

    public void setVille_depart_id(int ville_depart_id) {
        this.ville_depart_id = ville_depart_id;
    }

    public int getVille_arrivee_id() {
        return ville_arrivee_id;
    }

    public void setVille_arrivee_id(int ville_arrivee_id) {
        this.ville_arrivee_id = ville_arrivee_id;
    }

    public LocalDate getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(LocalDate date_depart) {
        this.date_depart = date_depart;
    }

    public LocalDate getDate_arrivee() {
        return date_arrivee;
    }

    public void setDate_arrivee(LocalDate date_arrivee) {
        this.date_arrivee = date_arrivee;
    }
}
