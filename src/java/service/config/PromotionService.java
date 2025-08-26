package service.config;

import entity.Reservation;
import entity.config.Promotion;
import service.DatabaseService;
import service.ReservationService;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class PromotionService {

    private final DatabaseService databaseService = new DatabaseService();

    public List<Promotion> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new Promotion(
                        rs.getInt("id"),
                        rs.getInt("id_vol"),
                        rs.getInt("id_type_siege"),
                        rs.getInt("nb_place"),
                        rs.getDouble("prix_promo"),
                        rs.getTimestamp("date_fin").toLocalDateTime()
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Reservation> getPaidReservationsForPromotion(Connection conn, Promotion promotion) {
        String paidReservationsQuery = "select r.*"
                + " from reservation r"
                + "     join promotion p on p.id = r.id_promotion"
                + " where id_etat_reservation = 1"
                + "     and p.id = " + promotion.getId();

        return new ReservationService().select(conn, paidReservationsQuery);
    }

    public List<Promotion> getPromotionForVol(Connection conn, int idVol, int idTypeSiege, LocalDateTime dateReservation) {
        String paidReservationsQuery = "select count(r.id) as nb_paid"
                + " from reservation r"
                + " join place_vol pv on pv.id = r.id_place_vol"
                + " where pv.id_vol = " + idVol
                + "     and id_type_siege = " + idTypeSiege
                + "     and id_etat_reservation = 1"
                + "     and id_promotion is not null";

        int nbPaid = this.databaseService.select(conn, paidReservationsQuery, rs -> {
            try {
                return rs.getInt("nb_paid");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).get(0);

        String query = "select * from promotion"
                + " where id_vol = " + idVol
                + "     and id_type_siege = " + idTypeSiege
                + "     and date_fin >= '" + dateReservation + "'"// still enough time
                + "     and nb_place - " + nbPaid + " > 0" // still enough place
                + " order by id asc limit 1";
        return this.select(conn, query);
    }

    public int insert(Connection conn, Promotion pourcentagePromotion) {
        return this.databaseService.insert(conn, "promotion", pourcentagePromotion);
    }
}
