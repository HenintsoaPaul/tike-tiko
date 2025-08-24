package views;

import java.time.LocalDateTime;

public class VReductionTrancheAge {

    int id;
    double val_pourcentage;
    LocalDateTime date_modification;
    int id_tranche_age;
    String nom_tranche_age;
    int age_min;
    int age_max;

    // Constr
    public VReductionTrancheAge() {
    }

    public VReductionTrancheAge(int id, double valPourcentage, LocalDateTime dateModification, int idTrancheAge,
                                String nomTrancheAge, int ageMin, int ageMax) {
        this.setId(id);
        this.setVal_pourcentage(valPourcentage);
        this.setDate_modification(dateModification);
        this.setId_tranche_age(idTrancheAge);
        this.setNom_tranche_age(nomTrancheAge);
        this.setAge_min(ageMin);
        this.setAge_max(ageMax);
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

    public String getNom_tranche_age() {
        return nom_tranche_age;
    }

    public void setNom_tranche_age(String nom_tranche_age) {
        this.nom_tranche_age = nom_tranche_age;
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

    public String getTranche() {
        return nom_tranche_age + " ( " + age_min + " ; " + age_max + " )";
    }
}
