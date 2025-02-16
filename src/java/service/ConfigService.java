package service;

import entity.config.MinNbHeureAnnulation;
import entity.config.MinNbHeureReservation;
import entity.config.PourcentagePromotion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ConfigService {

    private final DatabaseService databaseService = new DatabaseService();

    public List<PourcentagePromotion> selectPourcentagePromotion(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new PourcentagePromotion(
                        rs.getInt("id"),
                        rs.getInt("id_type_siege"),
                        rs.getDouble("val"),
                        rs.getTimestamp("date_modification").toLocalDateTime()
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

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
}
