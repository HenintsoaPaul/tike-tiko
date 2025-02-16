package service;

import entity.config.PourcentagePromotion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PourcentagePromotionService {

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

    public int insert(Connection conn, PourcentagePromotion pourcentagePromotion) {
        return this.databaseService.insert(conn, "pourcentage_promotion", pourcentagePromotion);
    }
}
