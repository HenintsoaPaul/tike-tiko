package service;

import entity.Vol;
import entity.config.MinNbHeureAnnulation;
import entity.config.MinNbHeureReservation;
import form.VolFilterFormData;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

    public Vol selectById(Connection conn, String id) {
        return this.select(conn, "select * from vol where id = " + id ).get(0);
    }

    public List<Vol> selectWithFilter(Connection conn, VolFilterFormData volFilterFormData) {
        String query = volFilterFormData.getFullFilterQuery();
        System.out.println("Filter query: " + query);
        return this.select(conn, query);
    }

    public int insert(Connection conn, Vol vol) {
        return this.databaseService.insert(conn, "vol", vol);
    }

    public LocalDateTime getLimiteReservation(Vol vol, MinNbHeureReservation minNbHeureReservation) {
        return vol.getHeure_depart()
                .minusHours((long) minNbHeureReservation.getVal());
    }

    public LocalDateTime getLimiteAnnulation(Vol vol, MinNbHeureAnnulation minNbHeureAnnulation) {
        return vol.getHeure_depart()
                .minusHours((long) minNbHeureAnnulation.getVal());
    }

    public void controller(Vol vol) throws Exception {
        // depart < arrivee
        if (!vol.getHeure_depart().isBefore(vol.getHeure_arrivee())) {
            throw new Exception("heure_depart must be before heure_arrivee");
        }

        // depart == arrivee
        if (vol.getHeure_depart() == vol.getHeure_arrivee()) {
            throw new Exception("heure_depart must be different to heure_arrivee");
        }
    }
}
