package service;

import views.VReservation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VReservationService {

    private final DatabaseService databaseService = new DatabaseService();

    public List<VReservation> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new VReservation(
                        rs.getInt("id"),
                        rs.getInt("id_vol"),
                        rs.getTimestamp("heure_reservation").toLocalDateTime(),
                        rs.getString("nom_type_siege"),
                        rs.getString("nom_etat_reservation"),
                        rs.getDouble("prix_avec_promo"),
                        rs.getDouble("prix_sans_promo"),
                        rs.getDouble("val_promo"),
                        rs.getBoolean("is_promotion"),
                        rs.getString("nom_client"),
                        rs.getString("img_passeport")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
