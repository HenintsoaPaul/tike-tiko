package dto;

import entity.Vol;
import service.ReservationService;

import java.sql.Connection;

public class PlaceDTO {
    int validatedBusiness;
    int validatedEco;

    int pendingBusiness;
    int pendingEco;

    // get n set
    public int getValidatedBusiness() {
        return validatedBusiness;
    }

    public void setValidatedBusiness(int validatedBusiness) {
        this.validatedBusiness = validatedBusiness;
    }

    public int getValidatedEco() {
        return validatedEco;
    }

    public void setValidatedEco(int validatedEco) {
        this.validatedEco = validatedEco;
    }

    public int getPendingBusiness() {
        return pendingBusiness;
    }

    public void setPendingBusiness(int pendingBusiness) {
        this.pendingBusiness = pendingBusiness;
    }

    public int getPendingEco() {
        return pendingEco;
    }

    public void setPendingEco(int pendingEco) {
        this.pendingEco = pendingEco;
    }

    // constr
    public PlaceDTO(Connection conn, ReservationService reservationService, Vol vol) {
        int idVol = vol.getId();

        int nbPlacesPrisBusiness = reservationService.getNbReservationConfirme(conn, 1, idVol),
                nbPlacesPrisEco = reservationService.getNbReservationConfirme(conn, 2, idVol);

        int nbPlacesAttenteBusiness = reservationService.getNbReservationAttente(conn, 1, idVol),
                nbPlacesAttenteEco = reservationService.getNbReservationAttente(conn, 2, idVol);

        this.validatedBusiness = nbPlacesPrisBusiness;
        this.validatedEco = nbPlacesPrisEco;
        this.pendingBusiness = nbPlacesAttenteBusiness;
        this.pendingEco = nbPlacesAttenteEco;
    }
}
