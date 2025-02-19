package service;

import entity.Reservation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReservationService {

    private final DatabaseService databaseService = new DatabaseService();

    public List<Reservation> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new Reservation(
                        rs.getInt("id"),
                        rs.getInt("id_etat_reservation"),
                        rs.getInt("id_place_vol"),
                        rs.getTimestamp("heure_reservation").toLocalDateTime(),
                        rs.getString("nom_client")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public int insert(Connection conn, Reservation reservation) {
        return this.databaseService.insert(conn, "reservation", reservation);
    }

    public int update(Connection conn, Reservation reservation) {
        return this.databaseService.update(conn, "reservation", reservation);
    }
}
