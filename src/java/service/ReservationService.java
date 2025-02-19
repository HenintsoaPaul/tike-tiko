package service;

import entity.Reservation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReservationService {

    private final DatabaseService databaseService = new DatabaseService();

    public List<Reservation> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new Reservation(
                        rs.getInt("id"),
                        rs.getInt("id_etat_reservation"),
                        rs.getInt("id_place_vol"),
                        rs.getTimestamp("heure_reservation").toLocalDateTime(),
                        rs.getString("nom_client"),
                        rs.getString("img_passeport")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Reservation selectById(Connection conn, String id) {
        String query = "select * from reservation where id = " + id;
        return this.select(conn, query).get(0);
    }

    public int getNbPlacesPris(Connection conn, int idTypeSiege) {
        String query = "select count(r.id) " +
                "from reservation r " +
                "         join place_vol pv on pv.id = r.id_place_vol " +
                "where r.nom_client is not null " +
                "  and id_type_siege =" + idTypeSiege;
        return this.databaseService.select(conn, query, rs -> {
            try {
                return rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).get(0);
    }

    public int insert(Connection conn, Reservation reservation) {
        return this.databaseService.insert(conn, "reservation", reservation);
    }

    public int update(Connection conn, Reservation reservation) {
        return this.databaseService.update(conn, "reservation", reservation);
    }
}
