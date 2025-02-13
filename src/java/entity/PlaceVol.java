package entity;

import src.summer.annotations.form.validation.Required;

public class PlaceVol {
    @Required
    int id;

    @Required
    int id_vol;

    @Required
    int id_type_siege;

    @Required
    int id_pourcentage_promotion;

    @Required
    double prix_sans_promo;

    double prix_avec_promo;

    @Required
    boolean is_promotion;

    // Constr
    public PlaceVol() {
    }

    // Getters n Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_vol() {
        return id_vol;
    }

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }

    public int getId_type_siege() {
        return id_type_siege;
    }

    public void setId_type_siege(int id_type_siege) {
        this.id_type_siege = id_type_siege;
    }

    public int getId_pourcentage_promotion() {
        return id_pourcentage_promotion;
    }

    public void setId_pourcentage_promotion(int id_pourcentage_promotion) {
        this.id_pourcentage_promotion = id_pourcentage_promotion;
    }

    public double getPrix_sans_promo() {
        return prix_sans_promo;
    }

    public void setPrix_sans_promo(double prix_sans_promo) {
        this.prix_sans_promo = prix_sans_promo;
    }

    public double getPrix_avec_promo() {
        return prix_avec_promo;
    }

    public void setPrix_avec_promo(double prix_avec_promo) {
        this.prix_avec_promo = prix_avec_promo;
    }

    public boolean getIs_promotion() {
        return is_promotion;
    }

    public void setIs_promotion(boolean is_promotion) {
        this.is_promotion = is_promotion;
    }
}
