package service;

import entity.Ville;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VilleService {

    DatabaseService databaseService = new DatabaseService();

    public List<Ville> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new Ville(
                        rs.getInt("id"),
                        rs.getString("nom")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
