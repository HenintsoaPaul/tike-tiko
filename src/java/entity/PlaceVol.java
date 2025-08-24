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
    String reference;

    // Constr
    public PlaceVol() {
    }

    public PlaceVol(int id, int idVol, int idTypeSiege, String reference) {
        this.id = id;
        this.id_vol = idVol;
        this.id_type_siege = idTypeSiege;
        this.reference = reference;
    }

    public PlaceVol(Vol vol, int idTypeSiege, String reference) {
        this.id_vol = vol.getId();
        this.id_type_siege = idTypeSiege;
        this.reference = reference;
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
}
