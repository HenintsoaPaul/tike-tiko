package form;

import java.time.LocalDateTime;

public class VolFilterFormData {
    LocalDateTime heure_depart_min;
    LocalDateTime heure_depart_max;

    LocalDateTime heure_arrivee_min;
    LocalDateTime heure_arrivee_max;

    int id_avion;
    int id_ville_depart;
    int id_ville_destination;

    // db
    public String getQuery() {
        String query = "select * from vol where 1=1";

        // heure_depart
        if (heure_depart_min != null) {
            query += " and heure_depart_min <= " + heure_depart_min;
        }
        if (heure_depart_max != null) {
            query += " and heure_depart_max >= " + heure_depart_max;
        }

        // heure_arrivee
        if (heure_arrivee_min != null) {
            query += " and heure_arrivee_min <= " + heure_arrivee_min;
        }
        if (heure_arrivee_max != null) {
            query += " and heure_arrivee_max >= " + heure_arrivee_max;
        }

        // avion
        if (id_avion != -1) {
            query += " and id_avion = " + id_avion;
        }

        // ville_depart
        if (id_ville_depart != -1) {
            query += " and id_ville_depart = " + id_ville_depart;
        }

        // ville_destination
        if (id_ville_destination != -1) {
            query += " and id_ville_destination = " + id_ville_destination;
        }

        return query;
    }

    // getters n setters
    public LocalDateTime getHeure_depart_min() {
        return heure_depart_min;
    }

    public void setHeure_depart_min(LocalDateTime heure_depart_min) {
        this.heure_depart_min = heure_depart_min;
    }

    public LocalDateTime getHeure_depart_max() {
        return heure_depart_max;
    }

    public void setHeure_depart_max(LocalDateTime heure_depart_max) {
        this.heure_depart_max = heure_depart_max;
    }

    public LocalDateTime getHeure_arrivee_min() {
        return heure_arrivee_min;
    }

    public void setHeure_arrivee_min(LocalDateTime heure_arrivee_min) {
        this.heure_arrivee_min = heure_arrivee_min;
    }

    public LocalDateTime getHeure_arrivee_max() {
        return heure_arrivee_max;
    }

    public void setHeure_arrivee_max(LocalDateTime heure_arrivee_max) {
        this.heure_arrivee_max = heure_arrivee_max;
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
}
