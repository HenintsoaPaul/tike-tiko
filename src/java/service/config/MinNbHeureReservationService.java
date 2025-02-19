package service.config;

import entity.config.MinNbHeureReservation;
import service.DatabaseService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MinNbHeureReservationService {

    private final DatabaseService databaseService = new DatabaseService();

    public List<MinNbHeureReservation> select(Connection conn, String query) {
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

    public List<MinNbHeureReservation> selectAll(Connection conn) {
        return select(conn, "select * from min_nb_heure_reservation order by id desc");
    }

    public MinNbHeureReservation selectCurrent(Connection conn) {
        String query = "select * from min_nb_heure_reservation order by id desc limit 1";
        return this.select(conn, query).get(0);
    }

    public int insert(Connection conn, MinNbHeureReservation minNbHeureReservation) {
        return this.databaseService.insert(conn, "min_nb_heure_reservation", minNbHeureReservation);
    }
}
