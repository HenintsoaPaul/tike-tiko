package service;

import entity.PlaceVol;
import entity.Vol;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlaceVolService {

    DatabaseService databaseService = new DatabaseService();

    public List<PlaceVol> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new PlaceVol(
                        rs.getInt("id"),
                        rs.getInt("id_vol"),
                        rs.getInt("id_type_siege"),
                        rs.getString("reference")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public PlaceVol selectNextPlaceLibre(Connection conn, int id_vol, int id_type_siege) {
        // La prochaine place libre est liee a aucune reservation dont l'etat est 2 ou 4(annulee).
        String query = "select * from place_vol where id_vol = " + id_vol
                + " and id_type_siege = " + id_type_siege
                + " and id not in (select id_place_vol from reservation where id_etat_reservation = 1 or id_etat_reservation = 3)"
                + " order by id asc limit 1";
        List<PlaceVol> ll = this.select(conn, query);
        return !ll.isEmpty() ? ll.get(0) : null;
    }

    public int insert(Connection conn, PlaceVol vol) {
        return this.databaseService.insert(conn, "place_vol", vol);
    }

    public int insertPlaces(
            Connection conn,
            Vol vol,
            int nbPlaces,
            int idTypeSiege
    ) {
        int rows = 0;

        for (int i = 0; i < nbPlaces; i++) {
            // TODO: generate unique reference
            String reference = vol.getId() + "_" + idTypeSiege + "_" + i;

            PlaceVol placeVol = new PlaceVol(vol, idTypeSiege, reference);

            int id = this.insert(conn, placeVol);
            rows += id == -1 ? 0 : 1;
        }

        return rows;
    }

    public int update(Connection conn, PlaceVol placeVol) {
        return this.databaseService.update(conn, "place_vol", placeVol);
    }
}
