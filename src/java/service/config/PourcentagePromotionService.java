package service.config;

import entity.config.PourcentagePromotion;
import service.DatabaseService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PourcentagePromotionService {

    private final DatabaseService databaseService = new DatabaseService();

    public List<PourcentagePromotion> select(Connection conn, String query) {
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

    public PourcentagePromotion selectPourcentagePromotionByIdTypeSiege(Connection conn, int idTypeSiege) {
        String query = "select * from pourcentage_promotion where id_type_siege = " + idTypeSiege + " order by id desc limit 1";
        return this.select(conn, query).get(0);
    }

    public int insert(Connection conn, PourcentagePromotion pourcentagePromotion) {
        return this.databaseService.insert(conn, "pourcentage_promotion", pourcentagePromotion);
    }
}
