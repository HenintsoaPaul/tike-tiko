package service.views;

import service.DatabaseService;
import views.VPromotion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VPromotionService {

    private final DatabaseService databaseService = new DatabaseService();

    public List<VPromotion> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new VPromotion(
                        rs.getInt("id"),
                        rs.getInt("id_vol"),
                        rs.getInt("id_type_siege"),
                        rs.getString("nom_type_siege"),
                        rs.getInt("nb_place"),
                        rs.getDouble("prix_promo"),
                        rs.getTimestamp("date_fin").toLocalDateTime()
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<VPromotion> selectAll(Connection conn) {
        return select(conn, "select * from v_promotion order by id desc");
    }
}
