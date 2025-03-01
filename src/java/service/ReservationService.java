package service;

import entity.Reservation;
import form.ReservationFormData;

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
                        rs.getString("img_passeport"),
                        rs.getInt("id_utilisateur"),
                        rs.getInt("id_reservation_mere")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Reservation selectById(Connection conn, String id) {
        String query = "select * from reservation where id = " + id;
        List<Reservation> ll = this.select(conn, query);
        return ll.isEmpty() ? null : ll.get(0);
    }

    /**
     * Retourne le nombre de place avec etat_reservation confirmee.
     */
    public int getNbReservationConfirme(Connection conn, int idTypeSiege, int idVol) {
        return this.getNbReservation(conn, idTypeSiege, idVol, 3);
    }

    /**
     * Retourne le nombre de place en fonction etat_reservation.
     */
    public int getNbReservation(Connection conn, int idTypeSiege, int idVol, int idEtatReservation) {
        String query = "select count(r.id) " +
                "from reservation r " +
                "         join place_vol pv on pv.id = r.id_place_vol " +
                "where r.id_etat_reservation = " + idEtatReservation +
                " and id_type_siege =" + idTypeSiege +
                " and id_vol = " + idVol;
        return this.databaseService.select(conn, query, rs -> {
            try {
                return rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).get(0);
    }

    /**
     * Retourne le nombre de reservation faits par un client sur un vol.
     */
    private int getNbReservationsFaits(Connection conn, int idUtilisateur, int idVol) {
        String reservationsUser = "select r.*" +
                "               from reservation r" +
                "                        join utilisateur u on r.id_utilisateur = u.id" +
                "               where u.id = " + idUtilisateur;

        String query = "select count(pv.id)" +
                "from place_vol pv" +
                "         join vol v on pv.id_vol = v.id" +
                "         join ("+reservationsUser+") rs on rs.id_place_vol = pv.id " +
                "where v.id = " + idVol;

        return this.databaseService.select(conn, query, rs -> {
            try {
                return rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).get(0);
    }

    /**
     * Retourne le nombre de reservation faits par un client sur un vol.
     */
    public int getNbReservationsFaits(Connection conn, ReservationFormData reservationFormData) {
        return this.getNbReservationsFaits(conn, reservationFormData.getId_client(), reservationFormData.getId_vol());
    }

    public int insert(Connection conn, Reservation reservation) {
        return this.databaseService.insert(conn, "reservation", reservation);
    }

    public int update(Connection conn, Reservation reservation) {
        return this.databaseService.update(conn, "reservation", reservation);
    }
}
