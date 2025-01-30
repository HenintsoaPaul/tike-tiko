package entity;

import src.summer.annotations.form.validation.Min;
import src.summer.annotations.form.validation.Required;

import java.time.LocalDate;

public class Avion {
    @Required
    int id;

    @Required
    String modele;

    @Required
    @Min( 1 )
    int siege_business;

    @Required
    @Min( 1 )
    int siege_eco;

    @Required
    LocalDate date_fabrication;

    // Constr
    public Avion() {}

    // Getters n Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getSiege_business() {
        return siege_business;
    }

    public void setSiege_business(int siege_business) {
        this.siege_business = siege_business;
    }

    public int getSiege_eco() {
        return siege_eco;
    }

    public void setSiege_eco(int siege_eco) {
        this.siege_eco = siege_eco;
    }

    public LocalDate getDate_fabrication() {
        return date_fabrication;
    }

    public void setDate_fabrication(LocalDate date_fabrication) {
        this.date_fabrication = date_fabrication;
    }
}
