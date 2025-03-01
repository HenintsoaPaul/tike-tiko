package dto;

import entity.Vol;
import entity.config.MinNbHeureAnnulation;
import entity.config.MinNbHeureReservation;
import entity.config.PourcentagePromotion;
import service.config.MinNbHeureAnnulationService;
import service.config.MinNbHeureReservationService;
import service.config.PourcentagePromotionService;

import java.sql.Connection;
import java.time.LocalDateTime;

public class ConfigDTO {

    PourcentagePromotion pourcentagePromotionBusiness;
    PourcentagePromotion pourcentagePromotionEco;
    MinNbHeureReservation minNbHeureReservation;
    MinNbHeureAnnulation minNbHeureAnnulation;

    LocalDateTime limiteReservation;
    LocalDateTime limiteAnnulation;

    // get n set
    public PourcentagePromotion getPourcentagePromotionBusiness() {
        return pourcentagePromotionBusiness;
    }

    public void setPourcentagePromotionBusiness(PourcentagePromotion pourcentagePromotionBusiness) {
        this.pourcentagePromotionBusiness = pourcentagePromotionBusiness;
    }

    public PourcentagePromotion getPourcentagePromotionEco() {
        return pourcentagePromotionEco;
    }

    public void setPourcentagePromotionEco(PourcentagePromotion pourcentagePromotionEco) {
        this.pourcentagePromotionEco = pourcentagePromotionEco;
    }

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
    public ConfigDTO(Connection conn, PourcentagePromotionService pourcentagePromotionService,
                     MinNbHeureReservationService minNbHeureReservationService, MinNbHeureAnnulationService minNbHeureAnnulationService,
                     Vol vol) {
        this.pourcentagePromotionBusiness = pourcentagePromotionService.selectPourcentagePromotionByIdTypeSiege(conn, 1);
        this.pourcentagePromotionEco = pourcentagePromotionService.selectPourcentagePromotionByIdTypeSiege(conn, 2);

        this.minNbHeureReservation = minNbHeureReservationService.selectCurrent(conn);
        this.minNbHeureAnnulation = minNbHeureAnnulationService.selectCurrent(conn);

        this.limiteReservation = vol.getHeure_depart().minusHours((long) minNbHeureReservation.getVal());
        this.limiteAnnulation = vol.getHeure_depart().minusHours((long) minNbHeureAnnulation.getVal());
    }
}
