package service;

import entity.config.MinNbHeureAnnulation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MinNbHeureAnnulationService {

    private final DatabaseService databaseService = new DatabaseService();

    public List<MinNbHeureAnnulation> selectMinNbHeureAnnulation(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new MinNbHeureAnnulation(
                        rs.getInt("id"),
                        rs.getDouble("val"),
                        rs.getTimestamp("date_modification").toLocalDateTime()
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public int insert(Connection conn, MinNbHeureAnnulation minNbHeureAnnulation) {
        return this.databaseService.insert(conn, "min_nb_heure_annulation", minNbHeureAnnulation);
    }
}
