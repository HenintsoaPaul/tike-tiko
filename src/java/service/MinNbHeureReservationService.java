package service;

import entity.config.MinNbHeureReservation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MinNbHeureReservationService {

    private final DatabaseService databaseService = new DatabaseService();

    public List<MinNbHeureReservation> selectMinNbHeureReservation(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new MinNbHeureReservation(
                        rs.getInt("id"),
                        rs.getDouble("val"),
                        rs.getTimestamp("date_modification").toLocalDateTime()
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public int insert(Connection conn, MinNbHeureReservation minNbHeureReservation) {
        return this.databaseService.insert(conn, "min_nb_heure_reservation", minNbHeureReservation);
    }
}
