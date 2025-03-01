package myControllers;

import dto.PlaceDTO;
import entity.Avion;
import entity.Vol;
import entity.config.MinNbHeureAnnulation;
import entity.config.MinNbHeureReservation;
import entity.config.PourcentagePromotion;
import form.VolFilterFormData;
import service.*;
import service.config.MinNbHeureAnnulationService;
import service.config.MinNbHeureReservationService;
import service.config.PourcentagePromotionService;
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
public class VolController {

    private final MinNbHeureReservationService minNbHeureReservationService = new MinNbHeureReservationService();
    private final MinNbHeureAnnulationService minNbHeureAnnulationService = new MinNbHeureAnnulationService();
    private final PourcentagePromotionService promotionService = new PourcentagePromotionService();
    private final PlaceVolService placeService = new PlaceVolService();
    private final ReservationService reservationService = new ReservationService();
    private final AvionService avionService = new AvionService();
    private final VilleService villeService = new VilleService();
    private final VolService volService = new VolService();
    private final VVolService vVolService = new VVolService();

    private final DatabaseService databaseService = new DatabaseService();

    private void fetchData(Connection conn, ModelView mv) {
        mv.addObject("avions", avionService.select(conn, "select * from avion"));
        mv.addObject("villes", villeService.select(conn, "select * from ville"));
        mv.addObject("vvols", this.vVolService.select(conn, "select * from v_vol"));
    }

    @Get
    @UrlMapping(url = "vol_list")
    public ModelView list() {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("bo/vol/vol_list.jsp", null);
            fetchData(conn, mv);
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Get
    @UrlMapping(url = "fo_vol_list")
    public ModelView fo_list() {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("fo/vol/vol_list.jsp", null);
            fetchData(conn, mv);

            mv.addObject("vvols", this.vVolService.select(conn, "select * from v_vol"));

            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Post
    @UrlMapping(url = "vol_filter")
    public ModelView filter(
//            @Validate(errorPage = "vol_list")
            @Param(name = "volFiltre") VolFilterFormData volFilterFormData
    ) {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("bo/vol/vol_list.jsp", null);
            mv.addObject("vvols", this.vVolService.selectWithFilter(conn, volFilterFormData));
            fetchData(conn, mv);
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Post
    @UrlMapping(url = "fo_vol_filter")
    public ModelView fo_filter(
//            @Validate(errorPage = "vol_list")
            @Param(name = "volFiltre") VolFilterFormData volFilterFormData
    ) {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("fo/vol/vol_list.jsp", null);
            mv.addObject("vvols", this.vVolService.selectWithFilter(conn, volFilterFormData));
            fetchData(conn, mv);
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Get
    @UrlMapping(url = "vol_add")
    @Authorized(roleLevel = 10)
    public ModelView add() {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("bo/vol/vol_add.jsp", null);
            fetchData(conn, mv);

            mv.addObject("vvols", this.vVolService.select(conn, "select * from v_vol"));
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Post
    @UrlMapping(url = "vol_save")
    @Authorized(roleLevel = 10)
    public String save(
            @Validate(errorPage = "vol_add")
            @Param(name = "vol") Vol vol
    ) throws SQLException {
        Connection conn = null;
        try {
            conn = databaseService.getConnection();
            conn.setAutoCommit(false);

            volService.controller(vol);

            // 1/ insert vol
            int idVol = this.volService.insert(conn, vol);
            vol.setId(idVol);
            System.out.println("Insert vol of id: " + idVol);

            // 2/ insert de toutes les place_vol pour le vol que l'on vient d'inserer
            Avion avion = avionService.selectById(conn, vol.getId_avion());
            int nbPlaceBusiness = avion.getSiege_business(),
                    nbPlaceEco = avion.getSiege_eco();

            PourcentagePromotion promoBusiness = this.promotionService.selectPourcentagePromotionByIdTypeSiege(conn, 1),
                    promoEco = this.promotionService.selectPourcentagePromotionByIdTypeSiege(conn, 2);

            int placesBusiness = placeService.insertPlacesBusiness(conn, vol, promoBusiness, nbPlaceBusiness);
            int placesEco = placeService.insertPlacesEco(conn, vol, promoEco, nbPlaceEco);

            System.out.println("Insert placesBusiness = " + placesBusiness);
            System.out.println("Insert placesEco = " + placesEco);

            conn.commit();

            return "redirect:GET:/vol_add";
        } catch (Exception e) {
            assert conn != null;
            conn.rollback();
            throw new RuntimeException(e);
        }
    }

    // BackOffice
    @Get
    @UrlMapping(url = "vol_detail")
    public ModelView vol_detail(
            @Param(name = "idVol") String idVol
    ) {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("bo/vol/vol_detail.jsp", null);

            Vol vol = this.volService.selectById(conn, idVol);
            mv.addObject("placeDTO", new PlaceDTO(conn, reservationService, vol));

            MinNbHeureReservation minNbHeureReservation = minNbHeureReservationService.selectCurrent(conn);
            mv.addObject("limiteReservation", vol.getHeure_depart().minusHours((long) minNbHeureReservation.getVal()));

            MinNbHeureAnnulation minNbHeureAnnulation = minNbHeureAnnulationService.selectCurrent(conn);
            mv.addObject("limiteAnnulation", vol.getHeure_depart().minusHours((long) minNbHeureAnnulation.getVal()));

            mv.addObject("v_vol", vVolService.selectById(conn, idVol));
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
