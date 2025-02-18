package service;

import entity.Avion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AvionService {

    DatabaseService databaseService = new DatabaseService();

    public List<Avion> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new Avion(
                        rs.getInt("id"),
                        rs.getString("modele"),
                        rs.getInt("siege_business"),
                        rs.getInt("siege_eco"),
                        rs.getDate("date_fabrication").toLocalDate()
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Avion getAvion(Connection conn, int idAvion) {
        String query = "select * from avion where id = " + idAvion;
        return this.select(conn, query).get(0);
    }
}
