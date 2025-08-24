package service.views;

import views.VReductionTrancheAge;
import service.DatabaseService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VReductionTrancheAgeService {

    private final DatabaseService databaseService = new DatabaseService();

    public List<VReductionTrancheAge> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new VReductionTrancheAge(
                        rs.getInt("id"),
                        rs.getDouble("val_pourcentage"),
                        rs.getTimestamp("date_modification").toLocalDateTime(),
                        rs.getInt("id_tranche_age"),
                        rs.getString("nom_tranche_age"),
                        rs.getInt("age_min"),
                        rs.getInt("age_max")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<VReductionTrancheAge> selectAll(Connection conn) {
        return select(conn, "select * from v_reduction_tranche_age order by id desc");
    }
}
