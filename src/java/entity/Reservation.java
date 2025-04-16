package entity;

import form.ReservationFormData;
import src.summer.annotations.form.validation.Required;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    @Required
    int id;

    @Required
    int id_etat_reservation;

    @Required
    int id_place_vol;

    @Required
    LocalDateTime heure_reservation;

    int id_utilisateur;
    int id_reduction_tranche_age;
    Integer id_reservation_mere;
    String img_passeport;

    double prix_final;

    // Constr pour ajouter une reservation depuis un vol
    public Reservation(PlaceVol placeVol, ReservationFormData reservationFormData, double prix_final) {
        this.setId_etat_reservation(3);
        this.setId_place_vol(placeVol.getId());
        this.setHeure_reservation(reservationFormData.getDate_reservation());
        this.setId_utilisateur(reservationFormData.getId_client());
        this.setId_reservation_mere(null);

        this.setPrix_final(prix_final);
    }

    // Constr pour mapper les lignes venant du bdd
    public Reservation(int id, int id_etat_reservation, int id_place_vol,
                       LocalDateTime heure_reservation, String img_passeport,
                       int id_utilisateur, int id_reduction_tranche_age, int id_reservation_mere, double prixFinal) {
        this.id = id;
        this.id_etat_reservation = id_etat_reservation;
        this.id_place_vol = id_place_vol;
        this.heure_reservation = heure_reservation;
        this.id_reduction_tranche_age = id_reduction_tranche_age;
        this.id_reservation_mere = id_reservation_mere;
        this.img_passeport = img_passeport;
        this.id_utilisateur = id_utilisateur;
        this.prix_final = prixFinal;
    }

    // Constr pour les annulations
    public Reservation(Reservation reservation, int id_etat_reservation) {
        this.id_etat_reservation = id_etat_reservation;

        this.setId_place_vol(reservation.getId_place_vol());
        this.setHeure_reservation(reservation.getHeure_reservation());
        this.setId_reservation_mere(reservation.getId_reservation_mere());
        this.setImg_passeport(reservation.getImg_passeport());
        this.setId_utilisateur(reservation.getId_utilisateur());
        this.setPrix_final(reservation.getPrix_final());
    }

    // Getters n Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_etat_reservation() {
        return id_etat_reservation;
    }

    public void setId_etat_reservation(int id_etat_reservation) {
        this.id_etat_reservation = id_etat_reservation;
    }

    public int getId_place_vol() {
        return id_place_vol;
    }

    public void setId_place_vol(int id_place_vol) {
        this.id_place_vol = id_place_vol;
    }

    public LocalDateTime getHeure_reservation() {
        return heure_reservation;
    }

    public void setHeure_reservation(LocalDateTime heure_reservation) {
        this.heure_reservation = heure_reservation;
    }

    public Integer getId_reservation_mere() {
        return id_reservation_mere;
    }

    public void setId_reservation_mere(Integer id_reservation_mere) {
        this.id_reservation_mere = id_reservation_mere;
    }

    public String getImg_passeport() {
        return img_passeport;
    }

    public void setImg_passeport(String img_passeport) {
        this.img_passeport = img_passeport;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public double getPrix_final() {
        return prix_final;
    }

    public void setPrix_final(double prix_final) {
        this.prix_final = prix_final;
    }

    public int getId_reduction_tranche_age() {
        return id_reduction_tranche_age;
    }

    public void setId_reduction_tranche_age(int id_reduction_tranche_age) {
        this.id_reduction_tranche_age = id_reduction_tranche_age;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Reservation{" +
                "id=" + id +
                ", id_etat_reservation=" + id_etat_reservation +
                ", id_place_vol=" + id_place_vol +
                ", heure_reservation=" + (heure_reservation != null ? heure_reservation.format(formatter) : "null") +
                ", id_utilisateur=" + id_utilisateur +
                ", id_reservation_mere=" + (id_reservation_mere != null ? id_reservation_mere : "null") +
                ", img_passeport='" + (img_passeport != null ? img_passeport : "null") + '\'' +
                '}';
    }
}
