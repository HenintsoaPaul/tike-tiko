package service.views;

import form.VolFilterFormData;
import service.DatabaseService;
import views.VVol;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VVolService {

    DatabaseService databaseService = new DatabaseService();

    public List<VVol> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new VVol(
                        rs.getInt("id"),
                        rs.getInt("id_avion"),
                        rs.getInt("id_ville_depart"),
                        rs.getInt("id_ville_arrivee"),
                        rs.getTimestamp("heure_depart").toLocalDateTime(),
                        rs.getTimestamp("heure_arrivee").toLocalDateTime(),
                        rs.getDouble("prix_place_business"),
                        rs.getDouble("prix_place_eco"),
                        rs.getInt("nb_place_business"),
                        rs.getInt("nb_place_eco"),
                        rs.getString("nom_ville_depart"),
                        rs.getString("nom_ville_arrivee")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<VVol> selectWithFilter(Connection conn, VolFilterFormData volFilterFormData) {
        String query = "select * from v_vol where 1=1 " + volFilterFormData.getEndFilterQuery();
        System.out.println("Filter query: " + query);
        return this.select(conn, query);
    }

    public VVol selectById(Connection conn, String id) {
        return this.select(conn, "select * from v_vol where id = " + id ).get(0);
    }
}
