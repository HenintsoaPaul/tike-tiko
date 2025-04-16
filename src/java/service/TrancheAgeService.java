package service;

import entity.TrancheAge;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TrancheAgeService {

    DatabaseService databaseService = new DatabaseService();

    public List<TrancheAge> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new TrancheAge(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getInt("age_min"),
                        rs.getInt("age_max")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<TrancheAge> selectAll(Connection conn) {
        return select(conn, "select * from tranche_age");
    }
}
