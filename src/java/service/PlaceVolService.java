package service;

import entity.PlaceVol;
import entity.Vol;
import entity.config.PourcentagePromotion;

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
                        rs.getInt("id_pourcentage_promotion"),
                        rs.getDouble("prix_sans_promo"),
                        rs.getDouble("prix_avec_promo"),
                        rs.getString("nom_client"),
                        rs.getBoolean("is_promotion")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public PlaceVol selectNextPlaceLibre(Connection conn, String id_vol, String id_type_siege) {
        String query = "select * from place_vol where nom_client is null and id_vol = " + id_vol
                + " and id_type_siege = " + id_type_siege +
                " order by id desc limit 1";
        List<PlaceVol> ll = this.select(conn, query);
        return !ll.isEmpty() ? ll.get(0) : null;
    }

    public int insert(Connection conn, PlaceVol vol) {
        return this.databaseService.insert(conn, "place_vol", vol);
    }

    public int insertPlaces(
            Connection conn,
            Vol vol,
            double prix,
            PourcentagePromotion pourcentagePromotion,
            int nbPlaces,
            int nbPlacesPromo,
            int idTypeSiege
    ) {
        int rows = 0;

        double ratePromo = pourcentagePromotion.getVal() / 100,
                prixPromo = prix * (1 - ratePromo);

        for (int i = 0; i < nbPlaces; i++) {
            PlaceVol placeVol = new PlaceVol(vol, idTypeSiege, pourcentagePromotion);

            placeVol.setPrix_sans_promo(prix);
            placeVol.setPrix_avec_promo(prixPromo);

            placeVol.setIs_promotion(i < nbPlacesPromo);

            int id = this.insert(conn, placeVol);
            rows += id == -1 ? 0 : 1;
        }

        return rows;
    }

    public int insertPlacesBusiness(Connection conn, Vol vol, PourcentagePromotion pourcentagePromotionBusiness, int nbPlaces) {
        return this.insertPlaces(
                conn,
                vol,
                vol.getPrix_place_business(),
                pourcentagePromotionBusiness,
                nbPlaces,
                vol.getNb_place_promo_business(),
                1
        );
    }

    public int insertPlacesEco(Connection conn, Vol vol, PourcentagePromotion pourcentagePromotionEco, int nbPlaces) {
        return this.insertPlaces(
                conn,
                vol,
                vol.getPrix_place_eco(),
                pourcentagePromotionEco,
                nbPlaces,
                vol.getNb_place_promo_eco(),
                2
        );
    }

    public int update(Connection conn, PlaceVol placeVol) {
        return this.databaseService.update(conn, "place_vol", placeVol);
    }
}
