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
                        rs.getBoolean("is_promotion")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public int insert(Connection conn, PlaceVol vol) {
        return this.databaseService.insert(conn, "place_vol", vol);
    }

    public int insertPlacesEco(Connection conn, Vol vol, PourcentagePromotion pourcentagePromotionEco, int nbPlaces) {
        int rows = 0;

        for (int i = 0; i < nbPlaces; i++) {
            PlaceVol placeVol = new PlaceVol(vol, 2, pourcentagePromotionEco);
            placeVol.setPrix_sans_promo(vol.getPrix_place_eco());
            placeVol.setPrix_avec_promo(vol.getPrix_place_eco() * (pourcentagePromotionEco.getVal() / 100));

            if (i < vol.getNb_place_promo_eco()) {
                placeVol.setIs_promotion(true);
            }

            rows += this.insert(conn, placeVol);
        }

        return rows;
    }

    public int insertPlacesBusiness(Connection conn, Vol vol, PourcentagePromotion pourcentagePromotionBusiness, int nbPlaces) {
        int rows = 0;

        for (int i = 0; i < nbPlaces; i++) {
            PlaceVol placeVol = new PlaceVol(vol, 1, pourcentagePromotionBusiness);
            placeVol.setPrix_sans_promo(vol.getPrix_place_business());
            placeVol.setPrix_avec_promo(vol.getPrix_place_business() * (pourcentagePromotionBusiness.getVal() / 100));

            if (i < vol.getNb_place_promo_business()) {
                placeVol.setIs_promotion(true);
            }

            rows += this.insert(conn, placeVol);
        }

        return rows;
    }
}
