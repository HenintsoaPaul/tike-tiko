package views;

import java.time.LocalDateTime;


public class VReservation {

    int id;
    int id_vol;
    LocalDateTime heure_reservation;
    String nom_type_siege;
    String nom_etat_reservation;
    double prix_final;
    int id_utilisateur;
    String nom_utilisateur;
    String img_passeport;
    int id_promotion;

    // Constr
    public VReservation(
            int id, int idVol, LocalDateTime heureReservation,
            String nomTypeSiege, String nomEtatReservation,
            double prixFinal,
            int idUtilisateur, String nom_utilisateur,
            String imgPasseport,
            int id_promotion
    ) {
        this.id = id;
        this.id_vol = idVol;
        this.heure_reservation = heureReservation;
        this.nom_type_siege = nomTypeSiege;
        this.nom_etat_reservation = nomEtatReservation;
        this.setPrix_final(prixFinal);
        this.id_utilisateur = idUtilisateur;
        this.setNom_utilisateur(nom_utilisateur);
        this.img_passeport = imgPasseport;
        this.setId_promotion(id_promotion);
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

    public double getPrix_final() {
        return prix_final;
    }

    public void setPrix_final(double prix_final) {
        this.prix_final = prix_final;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getImg_passeport() {
        return img_passeport;
    }

    public void setImg_passeport(String img_passeport) {
        this.img_passeport = img_passeport;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public int getId_promotion() {
        return id_promotion;
    }

    public String getId_promotionStr() {
        return id_promotion == 0 ? "-" : id_promotion + "";
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }
}
