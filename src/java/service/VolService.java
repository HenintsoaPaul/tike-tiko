package service;

import entity.Vol;
import form.VolFilterFormData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VolService {

    DatabaseService databaseService = new DatabaseService();

    public List<Vol> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new Vol(
                        rs.getInt("id"),
                        rs.getInt("id_avion"),
                        rs.getInt("id_ville_depart"),
                        rs.getInt("id_ville_destination"),
                        rs.getTimestamp("heure_depart").toLocalDateTime(),
                        rs.getTimestamp("heure_arrivee").toLocalDateTime(),
                        rs.getDouble("prix_place_business"),
                        rs.getDouble("prix_place_eco"),
                        rs.getInt("nb_place_promo_business"),
                        rs.getInt("nb_place_promo_eco")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Vol> selectWithFilter(Connection conn, VolFilterFormData volFilterFormData) {
        String query = volFilterFormData.getQuery();
        System.out.println("Filter query: " + query);
        return this.select(conn, query);
    }

    public int insert(Connection conn, Vol vol) {
        return this.databaseService.insert(conn, "vol", vol);
    }

}
