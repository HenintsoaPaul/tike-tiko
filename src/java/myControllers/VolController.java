package myControllers;

import entity.Avion;
import entity.Vol;
import entity.config.PourcentagePromotion;
import service.*;
import src.summer.annotations.Param;
import src.summer.annotations.Validate;
import src.summer.annotations.controller.Controller;
import src.summer.annotations.controller.UrlMapping;
import src.summer.annotations.controller.verb.Get;
import src.summer.annotations.controller.verb.Post;
import src.summer.beans.ModelView;

import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class VolController {

    private final PourcentagePromotionService promotionService = new PourcentagePromotionService();
    private final PlaceVolService placeService = new PlaceVolService();

    private final AvionService avionService = new AvionService();
    private final VilleService villeService = new VilleService();
    private final VolService volService = new VolService();

    private final DatabaseService databaseService = new DatabaseService();

    private void fetchData(Connection conn, ModelView mv) {
        mv.addObject("avions", avionService.select(conn, "select * from avion"));
        mv.addObject("villes", villeService.select(conn, "select * from ville"));
    }

    @Get
    @UrlMapping(url = "vol_list")
    public ModelView list() {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("bo/vol/vol_list.jsp", null);
            fetchData(conn, mv);

            mv.addObject("vols", volService.select(conn, "select * from vol"));

            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Get
    @UrlMapping(url = "vol_add")
    public ModelView add() {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("bo/vol/vol_add.jsp", null);
            fetchData(conn, mv);
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Post
    @UrlMapping(url = "vol_save")
    public ModelView save(
            @Validate @Param(name = "vol") Vol vol
    ) {
        try (Connection conn = databaseService.getConnection()) {
            // 1/ insert vol
            this.volService.insert(conn, vol);

            // 2/ insert de toutes les place_vol pour le vol que l'on vient d'inserer
            Avion avion = avionService.getAvion(conn, vol.getId_avion());
            int nbPlaceBusiness = avion.getSiege_business(),
                    nbPlaceEco = avion.getSiege_eco();

            PourcentagePromotion promoBusiness = this.promotionService.selectPourcentagePromotionByIdTypeSiege(conn, 1),
                    promoEco = this.promotionService.selectPourcentagePromotionByIdTypeSiege(conn, 2);

            int placesBusiness = placeService.insertPlacesBusiness(conn, vol, promoBusiness, nbPlaceBusiness);
            int placesEco = placeService.insertPlacesEco(conn, vol, promoEco, nbPlaceEco);

            System.out.println("placesBusiness = " + placesBusiness);
            System.out.println("placesEco = " + placesEco);
            // todo ...

            ModelView mv = new ModelView("bo/vol/vol_add.jsp", null);
            fetchData(conn, mv);
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
