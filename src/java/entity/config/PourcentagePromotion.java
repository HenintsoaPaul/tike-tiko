package entity.config;

import src.summer.annotations.form.validation.Min;
import src.summer.annotations.form.validation.Required;

import java.time.LocalDateTime;

public class PourcentagePromotion {
    @Required
    int id;

    @Required
    int id_type_siege;

    @Required
    // annotation min/max/range
    @Min(0)
    double val;

    @Required
    LocalDateTime date_modification;


    // Constr
    public PourcentagePromotion() {
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
}
