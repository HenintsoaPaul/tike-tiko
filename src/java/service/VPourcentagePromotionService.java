package service;

import views.VPourcentagePromotion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VPourcentagePromotionService {

    private final DatabaseService databaseService = new DatabaseService();

    public List<VPourcentagePromotion> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new VPourcentagePromotion(
                        rs.getInt("id"),
                        rs.getInt("id_type_siege"),
                        rs.getString("nom_type_siege"),
                        rs.getDouble("val"),
                        rs.getTimestamp("date_modification").toLocalDateTime()
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<VPourcentagePromotion> selectAll(Connection conn) {
        return select(conn, "select * from v_pourcentage_promotion order by id desc");
    }
}
