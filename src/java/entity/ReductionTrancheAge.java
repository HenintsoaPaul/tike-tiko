package entity;

import java.time.LocalDateTime;

public class ReductionTrancheAge {

    int id;
    double val_pourcentage;
    LocalDateTime date_modification;
    int id_tranche_age;

    // Constr
    public ReductionTrancheAge() {
    }

    public ReductionTrancheAge(int id, double valPourcentage, LocalDateTime dateModification, int idTrancheAge) {
        this.setId(id);
        this.setVal_pourcentage(valPourcentage);
        this.setDate_modification(dateModification);
        this.setId_tranche_age(idTrancheAge);
    }

    // Getters n Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return a value in [0;100]
     */
    public double getVal_pourcentage() {
        return val_pourcentage;
    }

    public void setVal_pourcentage(double val_pourcentage) {
        this.val_pourcentage = val_pourcentage;
    }

    public LocalDateTime getDate_modification() {
        return date_modification;
    }

    public void setDate_modification(LocalDateTime date_modification) {
        this.date_modification = date_modification;
    }

    public int getId_tranche_age() {
        return id_tranche_age;
    }

    public void setId_tranche_age(int id_tranche_age) {
        this.id_tranche_age = id_tranche_age;
    }
}
