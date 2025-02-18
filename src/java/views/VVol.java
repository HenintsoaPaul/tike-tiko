package views;

import java.time.LocalDateTime;

public class VVol {

    int id;

    int id_avion;
    int id_ville_depart;
    int id_ville_destination;

    LocalDateTime heure_depart;
    LocalDateTime heure_arrivee;

    double prix_place_business;
    double prix_place_eco;

    int nb_place_promo_business;
    int nb_place_promo_eco;

    String nom_ville_depart;
    String nom_ville_destination;

    public VVol(int id, int idAvion, int idVilleDepart, int idVilleDestination, LocalDateTime heureDepart, LocalDateTime heureArrivee, double prixPlaceBusiness, double prixPlaceEco, int nbPlacePromoBusiness, int nbPlacePromoEco, String nomVilleDepart, String nomVilleDestination) {
        this.id = id;
        this.id_avion = idAvion;
        this.id_ville_depart = idVilleDepart;
        this.id_ville_destination = idVilleDestination;
        this.heure_depart = heureDepart;
        this.heure_arrivee = heureArrivee;
        this.prix_place_business = prixPlaceBusiness;
        this.prix_place_eco = prixPlaceEco;
        this.nb_place_promo_business = nbPlacePromoBusiness;
        this.nb_place_promo_eco = nbPlacePromoEco;
        this.nom_ville_depart = nomVilleDepart;
        this.nom_ville_destination = nomVilleDestination;
    }

    // getters n setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_avion() {
        return id_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public int getId_ville_depart() {
        return id_ville_depart;
    }

    public void setId_ville_depart(int id_ville_depart) {
        this.id_ville_depart = id_ville_depart;
    }

    public int getId_ville_destination() {
        return id_ville_destination;
    }

    public void setId_ville_destination(int id_ville_destination) {
        this.id_ville_destination = id_ville_destination;
    }

    public LocalDateTime getHeure_depart() {
        return heure_depart;
    }

    public void setHeure_depart(LocalDateTime heure_depart) {
        this.heure_depart = heure_depart;
    }

    public LocalDateTime getHeure_arrivee() {
        return heure_arrivee;
    }

    public void setHeure_arrivee(LocalDateTime heure_arrivee) {
        this.heure_arrivee = heure_arrivee;
    }

    public double getPrix_place_business() {
        return prix_place_business;
    }

    public void setPrix_place_business(double prix_place_business) {
        this.prix_place_business = prix_place_business;
    }

    public double getPrix_place_eco() {
        return prix_place_eco;
    }

    public void setPrix_place_eco(double prix_place_eco) {
        this.prix_place_eco = prix_place_eco;
    }

    public int getNb_place_promo_business() {
        return nb_place_promo_business;
    }

    public void setNb_place_promo_business(int nb_place_promo_business) {
        this.nb_place_promo_business = nb_place_promo_business;
    }

    public int getNb_place_promo_eco() {
        return nb_place_promo_eco;
    }

    public void setNb_place_promo_eco(int nb_place_promo_eco) {
        this.nb_place_promo_eco = nb_place_promo_eco;
    }

    public String getNom_ville_depart() {
        return nom_ville_depart;
    }

    public void setNom_ville_depart(String nom_ville_depart) {
        this.nom_ville_depart = nom_ville_depart;
    }

    public String getNom_ville_destination() {
        return nom_ville_destination;
    }

    public void setNom_ville_destination(String nom_ville_destination) {
        this.nom_ville_destination = nom_ville_destination;
    }
}
