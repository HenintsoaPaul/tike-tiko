package service;

import entity.TypeSiege;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TypeSiegeService {

    DatabaseService databaseService = new DatabaseService();

    public List<TypeSiege> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new TypeSiege(
                        rs.getInt("id"),
                        rs.getString("nom")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
