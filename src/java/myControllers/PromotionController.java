package myControllers;

import entity.config.Promotion;
import service.DatabaseService;
import service.TypeSiegeService;
import service.VolService;
import service.config.PromotionService;
import service.views.VPromotionService;
import src.summer.annotations.Authorized;
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
public class PromotionController {

    private final VolService volService = new VolService();
    private final TypeSiegeService typeSiegeService = new TypeSiegeService();
    private final PromotionService promotionService = new PromotionService();

    private final VPromotionService vPromotionService = new VPromotionService();

    private final DatabaseService databaseService = new DatabaseService();

    private void fetchData(Connection conn, ModelView mv) {
        mv.addObject("vPromotions", vPromotionService.selectAll(conn));
    }

    @Authorized
    @Get
    @UrlMapping(url = "promotion_list")
    public ModelView list() {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("bo/promotion/promotion_list.jsp", null);
            fetchData(conn, mv);
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Authorized
    @Get
    @UrlMapping(url = "fo_promotion_list")
    public ModelView fo_list() {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("fo/promotion/promotion_list.jsp", null);
            fetchData(conn, mv);
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Post
//    @UrlMapping(url = "promotion_filter")
//    public ModelView filter(
////            @Validate(errorPage = "promotion_list")
//            @Param(name = "volFiltre") VolFilterFormData volFilterFormData
//    ) {
//        try (Connection conn = databaseService.getConnection()) {
//            ModelView mv = new ModelView("bo/promotion/promotion_list.jsp", null);
//            mv.addObject("vvols", this.vVolService.selectWithFilter(conn, volFilterFormData));
//            fetchData(conn, mv);
//            return mv;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Post
//    @UrlMapping(url = "fo_promotion_filter")
//    public ModelView fo_filter(
////            @Validate(errorPage = "promotion_list")
//            @Param(name = "volFiltre") VolFilterFormData volFilterFormData
//    ) {
//        try (Connection conn = databaseService.getConnection()) {
//            ModelView mv = new ModelView("fo/promotion/promotion_list.jsp", null);
//            mv.addObject("vvols", this.vVolService.selectWithFilter(conn, volFilterFormData));
//            fetchData(conn, mv);
//            return mv;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Get
    @UrlMapping(url = "promotion_add")
    @Authorized(roleLevel = 10)
    public ModelView add() {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("bo/promotion/promotion_add.jsp", null);

            mv.addObject("vols", this.volService.select(conn, "select * from vol"));
            mv.addObject("typeSieges", this.typeSiegeService.selectAll(conn));

            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Post
    @UrlMapping(url = "promotion_save")
    @Authorized(roleLevel = 10)
    public String save(
            @Validate(errorPage = "promotion_add")
            @Param(name = "promotion") Promotion promotion
    ) throws SQLException {
        Connection conn = null;
        try {
            conn = databaseService.getConnection();
            conn.setAutoCommit(false);

            this.promotionService.insert(conn, promotion);
            System.out.println("Insert promotion done...");

            conn.commit();

            return "redirect:GET:/promotion_add";
        } catch (Exception e) {
            assert conn != null;
            conn.rollback();
            throw new RuntimeException(e);
        }
    }
//
//    private void insertPlacesOfNewVol(Connection conn, Vol vol) {
//        Avion avion = avionService.selectById(conn, vol.getId_avion());
//        int nbPlaceBusiness = avion.getSiege_business(),
//                nbPlaceEco = avion.getSiege_eco();
//
//        int placesBusiness = placeService.insertPlaces(conn, vol, nbPlaceBusiness, 1);
//        int placesEco = placeService.insertPlaces(conn, vol, nbPlaceEco, 2);
//
//        System.out.println("Insert placesBusiness = " + placesBusiness);
//        System.out.println("Insert placesEco = " + placesEco);
//    }
//
//    // BackOffice
//    @Get
//    @UrlMapping(url = "promotion_detail")
//    public ModelView promotion_detail(
//            @Param(name = "idVol") String idVol
//    ) {
//        try (Connection conn = databaseService.getConnection()) {
//            ModelView mv = new ModelView("bo/promotion/promotion_detail.jsp", null);
//
//            Vol vol = this.volService.selectById(conn, idVol);
//            mv.addObject("placeDTO", new PlaceDTO(conn, reservationService, vol));
//
//            MinNbHeureReservation minNbHeureReservation = minNbHeureReservationService.selectCurrent(conn);
//            mv.addObject("limiteReservation", vol.getHeure_depart().minusHours((long) minNbHeureReservation.getVal()));
//
//            MinNbHeureAnnulation minNbHeureAnnulation = minNbHeureAnnulationService.selectCurrent(conn);
//            mv.addObject("limiteAnnulation", vol.getHeure_depart().minusHours((long) minNbHeureAnnulation.getVal()));
//
//            mv.addObject("v_vol", vVolService.selectById(conn, idVol));
//            return mv;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
