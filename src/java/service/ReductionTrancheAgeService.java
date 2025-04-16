package service;

import entity.ReductionTrancheAge;
import form.ReservationFormData;

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

    private ReductionTrancheAge selectCurrent(Connection conn, int idTrancheAge) {
        return this.selectCurrentByTrancheAge(conn, idTrancheAge);
    }

    public double applyReduction(Connection conn, int idTrancheAge, double prix_final) {
        System.out.println("Prix final (sans reduction tranche): " + prix_final);

        ReductionTrancheAge reductionTrancheAge = this.selectCurrent(conn, idTrancheAge);
        prix_final = prix_final - (prix_final * reductionTrancheAge.getVal_pourcentage() / 100);

        System.out.println("Prix final (avec reduction tranche): " + prix_final + " | reduction: " + reductionTrancheAge.getVal_pourcentage());

        return prix_final;
    }
}
