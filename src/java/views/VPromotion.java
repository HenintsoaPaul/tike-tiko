package views;

import java.time.LocalDateTime;

public class VPromotion {
    int id;

    int id_vol;

    int id_type_siege;
    String nom_type_siege;

    int nb_place;

    double prix_promo;

    LocalDateTime date_fin;

    // Constr
    public VPromotion() {
    }

    public VPromotion(int id, int id_vol, int id_type_siege, String nom_type_siege, int nb_place, double prix_promo, LocalDateTime date_fin) {
        this.id = id;
        this.id_vol = id_vol;
        this.id_type_siege = id_type_siege;
        this.nom_type_siege = nom_type_siege;
        this.nb_place = nb_place;
        this.prix_promo = prix_promo;
        this.date_fin = date_fin;
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

    public LocalDateTime getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDateTime date_fin) {
        this.date_fin = date_fin;
    }

    public double getPrix_promo() {
        return prix_promo;
    }

    public void setPrix_promo(double prix_promo) {
        this.prix_promo = prix_promo;
    }

    public int getId_vol() {
        return id_vol;
    }

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }

    public String getNom_type_siege() {
        return nom_type_siege;
    }

    public void setNom_type_siege(String nom_type_siege) {
        this.nom_type_siege = nom_type_siege;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }
}
