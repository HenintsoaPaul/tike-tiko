package service;

import entity.Utilisateur;
import views.VReservation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VReservationService {

    private final DatabaseService databaseService = new DatabaseService();

    public List<VReservation> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new VReservation(
                        rs.getInt("id"),
                        rs.getInt("id_vol"),
                        rs.getTimestamp("heure_reservation").toLocalDateTime(),
                        rs.getString("nom_type_siege"),
                        rs.getString("nom_etat_reservation"),
                        rs.getDouble("prix_final"),
                        rs.getInt("id_utilisateur"),
                        rs.getInt("id_reservation_mere"),
                        rs.getString("img_passeport")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public VReservation selectById(Connection conn, String id) {
        List<VReservation> ll = this.select(conn, "select * from v_reservation where id = " + id);
        return ll.isEmpty() ? null : ll.get(0);
    }

    public List<VReservation> selectByUtilisateur(Connection conn, Utilisateur u) {
        return this.select(conn, "select * from v_reservation where id_utilisateur = " + u.getId());
    }
}
