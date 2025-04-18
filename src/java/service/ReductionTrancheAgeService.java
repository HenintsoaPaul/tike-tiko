package service;

import entity.ReductionTrancheAge;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReductionTrancheAgeService {

    DatabaseService databaseService = new DatabaseService();

    public List<ReductionTrancheAge> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new ReductionTrancheAge(
                        rs.getInt("id"),
                        rs.getDouble("val_pourcentage"),
                        rs.getTimestamp("date_modification").toLocalDateTime(),
                        rs.getInt("id_tranche_age")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public ReductionTrancheAge selectCurrentByTrancheAge(Connection conn, int idTrancheAge) {
        return select(conn, "select * from v_curr_reduction_tranche_age where id_tranche_age = " + idTrancheAge).get(0);
    }

    public double applyReduction(ReductionTrancheAge rta, double prix_final) {
        System.out.println("Prix final (sans reduction tranche): " + prix_final);

        prix_final = prix_final - (prix_final * rta.getVal_pourcentage() / 100);

        System.out.println("Prix final (avec reduction tranche): " + prix_final + " | reduction: " + rta.getVal_pourcentage());

        return prix_final;
    }
}
