package dto;

import entity.Vol;
import entity.config.MinNbHeureAnnulation;
import entity.config.MinNbHeureReservation;
import service.config.MinNbHeureAnnulationService;
import service.config.MinNbHeureReservationService;

import java.sql.Connection;
import java.time.LocalDateTime;

public class ConfigDTO {

    MinNbHeureReservation minNbHeureReservation;
    MinNbHeureAnnulation minNbHeureAnnulation;

    LocalDateTime limiteReservation;
    LocalDateTime limiteAnnulation;

    // get n set
    public MinNbHeureReservation getMinNbHeureReservation() {
        return minNbHeureReservation;
    }

    public void setMinNbHeureReservation(MinNbHeureReservation minNbHeureReservation) {
        this.minNbHeureReservation = minNbHeureReservation;
    }

    public MinNbHeureAnnulation getMinNbHeureAnnulation() {
        return minNbHeureAnnulation;
    }

    public void setMinNbHeureAnnulation(MinNbHeureAnnulation minNbHeureAnnulation) {
        this.minNbHeureAnnulation = minNbHeureAnnulation;
    }

    public LocalDateTime getLimiteReservation() {
        return limiteReservation;
    }

    public void setLimiteReservation(LocalDateTime limiteReservation) {
        this.limiteReservation = limiteReservation;
    }

    public LocalDateTime getLimiteAnnulation() {
        return limiteAnnulation;
    }

    public void setLimiteAnnulation(LocalDateTime limiteAnnulation) {
        this.limiteAnnulation = limiteAnnulation;
    }

    // constr
    public ConfigDTO(Connection conn, MinNbHeureReservationService minNbHeureReservationService, MinNbHeureAnnulationService minNbHeureAnnulationService, Vol vol) {
        this.minNbHeureReservation = minNbHeureReservationService.selectCurrent(conn);
        this.minNbHeureAnnulation = minNbHeureAnnulationService.selectCurrent(conn);

        this.limiteReservation = vol.getHeure_depart().minusHours((long) minNbHeureReservation.getVal());
        this.limiteAnnulation = vol.getHeure_depart().minusHours((long) minNbHeureAnnulation.getVal());
    }
}
