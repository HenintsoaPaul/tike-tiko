package views;

import java.time.LocalDateTime;


public class VReservation {

    int id;
    int id_vol;
    LocalDateTime heure_reservation;
    String nom_type_siege;
    String nom_etat_reservation;
    double prix_avec_promo;
    double prix_sans_promo;
    double val_promo;
    boolean is_promotion;
    String nom_client;
    String img_passeport;

    // Constr
    public VReservation(int id, int idVol, LocalDateTime heureReservation, String nomTypeSiege, String nomEtatReservation,
                        double prixAvecPromotion, double prixSansPromotion, double valPromo, boolean isPromotion,
                        String nomClient, String imgPasseport) {
        this.id = id;
        this.id_vol = idVol;
        this.heure_reservation = heureReservation;
        this.nom_type_siege = nomTypeSiege;
        this.nom_etat_reservation = nomEtatReservation;
        this.prix_avec_promo = prixAvecPromotion;
        this.prix_sans_promo = prixSansPromotion;
        this.val_promo = valPromo;
        this.is_promotion = isPromotion;
        this.nom_client = nomClient;
        this.img_passeport = imgPasseport;
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

    public LocalDateTime getHeure_reservation() {
        return heure_reservation;
    }

    public void setHeure_reservation(LocalDateTime heure_reservation) {
        this.heure_reservation = heure_reservation;
    }

    public String getNom_type_siege() {
        return nom_type_siege;
    }

    public void setNom_type_siege(String nom_type_siege) {
        this.nom_type_siege = nom_type_siege;
    }

    public String getNom_etat_reservation() {
        return nom_etat_reservation;
    }

    public void setNom_etat_reservation(String nom_etat_reservation) {
        this.nom_etat_reservation = nom_etat_reservation;
    }

    public double getPrix_avec_promo() {
        return prix_avec_promo;
    }

    public void setPrix_avec_promo(double prix_avec_promo) {
        this.prix_avec_promo = prix_avec_promo;
    }

    public double getPrix_sans_promo() {
        return prix_sans_promo;
    }

    public void setPrix_sans_promo(double prix_sans_promo) {
        this.prix_sans_promo = prix_sans_promo;
    }

    public boolean isIs_promotion() {
        return is_promotion;
    }

    public void setIs_promotion(boolean is_promotion) {
        this.is_promotion = is_promotion;
    }

    public double getVal_promo() {
        return val_promo;
    }

    public void setVal_promo(double val_promo) {
        this.val_promo = val_promo;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getImg_passeport() {
        return img_passeport;
    }

    public void setImg_passeport(String img_passeport) {
        this.img_passeport = img_passeport;
    }
}
