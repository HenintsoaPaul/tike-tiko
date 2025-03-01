package entity;

import src.summer.annotations.form.validation.IntRange;
import src.summer.annotations.form.validation.Min;
import src.summer.annotations.form.validation.Required;

import java.time.LocalDateTime;

public class Vol {
    @Required
    int id;

    @Required
    int id_avion;

    @Required
    int id_ville_depart;

    @Required
    int id_ville_destination;

    @Required
    LocalDateTime heure_depart;

    @Required
    LocalDateTime heure_arrivee;

    @Min(50.0)
    @Required
    double prix_place_business;

    @Min(50.0)
    @Required
    double prix_place_eco;

    @IntRange(minValue = 0, maxValue = 10)
    @Required
    int nb_place_promo_business;

    @IntRange(minValue = 0, maxValue = 50)
    @Required
    int nb_place_promo_eco;

    // Constr
    public Vol() {
    }

    public Vol(int id, int idAvion, int idVilleDepart, int idVilleDestination, LocalDateTime heureDepart, LocalDateTime heureArrivee, double prixPlaceBusiness, double prixPlaceEco, int nbPlacePromoBusiness, int nbPlacePromoEco) {
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
    }

    // Getters n Setters
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
}
