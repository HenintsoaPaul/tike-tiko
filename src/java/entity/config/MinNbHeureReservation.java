package entity.config;

import src.summer.annotations.form.validation.Min;
import src.summer.annotations.form.validation.Required;

import java.time.LocalDateTime;

public class MinNbHeureReservation {
    @Required
    int id;

    @Required
    // annotation min/max/range
    @Min(0)
    double val;

    @Required
    LocalDateTime date_modification;


    // Constr
    public MinNbHeureReservation() {
    }

    public MinNbHeureReservation(int id, double val, LocalDateTime date_modification) {
        this.id = id;
        this.val = val;
        this.date_modification = date_modification;
    }

    // Getters n Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
