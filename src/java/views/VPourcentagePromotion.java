package views;

import src.summer.annotations.form.validation.Min;
import src.summer.annotations.form.validation.Required;

import java.time.LocalDateTime;

public class VPourcentagePromotion {
    @Required
    int id;

    int id_type_siege;

    String nom_type_siege;

    // annotation min/max/range
    @Required
    @Min(0)
    double val;

    @Required
    LocalDateTime date_modification;


    // Constr
    public VPourcentagePromotion(int id, int idTypeSiege, String nomTypeSiege, double val, LocalDateTime dateModification) {
        this.id = id;
        this.id_type_siege = idTypeSiege;
        this.nom_type_siege = nomTypeSiege;
        this.val = val;
        this.date_modification = dateModification;
    }

    // Getters n Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_type_siege() {
        return id_type_siege;
    }

    public void setId_type_siege(int id_type_siege) {
        this.id_type_siege = id_type_siege;
    }

    public LocalDateTime getDate_modification() {
        return date_modification;
    }

    public void setDate_modification(LocalDateTime date_modification) {
        this.date_modification = date_modification;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public String getNom_type_siege() {
        return nom_type_siege;
    }

    public void setNom_type_siege(String nom_type_siege) {
        this.nom_type_siege = nom_type_siege;
    }
}
