package entity;

import src.summer.annotations.form.validation.Min;
import src.summer.annotations.form.validation.Required;

import java.time.LocalDateTime;

public class Promotion {
    @Required
    int id;

    @Required
    @Min(1)
    int vol_id;

    @Required
    String type_siege;

    @Required
    LocalDateTime date_debut;

    @Required
    LocalDateTime date_fin;

    // Constr
    public Promotion() {
    }

    // Getters n Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVol_id() {
        return vol_id;
    }

    public void setVol_id(int vol_id) {
        this.vol_id = vol_id;
    }

    public String getType_siege() {
        return type_siege;
    }

    public void setType_siege(String type_siege) {
        this.type_siege = type_siege;
    }

    public LocalDateTime getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDateTime date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDateTime getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDateTime date_fin) {
        this.date_fin = date_fin;
    }
}
