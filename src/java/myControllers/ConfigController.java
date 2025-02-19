package myControllers;

import entity.config.MinNbHeureAnnulation;
import entity.config.MinNbHeureReservation;
import entity.config.PourcentagePromotion;
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
import java.time.LocalDateTime;

@Controller
public class ConfigController {

    private final VPourcentagePromotionService vpPromotionService = new VPourcentagePromotionService();
    private final PourcentagePromotionService pourcentagePromotionService = new PourcentagePromotionService();
    private final MinNbHeureReservationService minNbHeureReservationService = new MinNbHeureReservationService();
    private final MinNbHeureAnnulationService minNbHeureAnnulationService = new MinNbHeureAnnulationService();

    private final TypeSiegeService typeSiegeService = new TypeSiegeService();

    private final DatabaseService databaseService = new DatabaseService();

    private void fetchData(Connection conn, ModelView mv) {
        mv.addObject("typeSieges", typeSiegeService.select(conn, "select * from type_siege"));

        mv.addObject("vPourcentagePromotions", vpPromotionService.select(conn, "select * from v_pourcentage_promotion order by id desc"));
        mv.addObject("minNbHeureReservations", minNbHeureReservationService.select(conn, "select * from min_nb_heure_reservation order by id desc"));
        mv.addObject("minNbHeureAnnulations", minNbHeureAnnulationService.select(conn, "select * from min_nb_heure_annulation order by id desc"));
    }

    @Authorized
    @Get
    @UrlMapping(url = "config")
    public ModelView list() {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("bo/config.jsp", null);
            fetchData(conn, mv);
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Authorized
    @Post
    @UrlMapping(url = "config_pourcentage")
    public ModelView pourcentagePromotion(
            @Validate @Param(name = "pourcentagePromotion") PourcentagePromotion pourcentagePromotion
    ) {
        try (Connection conn = databaseService.getConnection()) {
            // traitement...
            pourcentagePromotion.setDate_modification(LocalDateTime.now());
            int rows = this.pourcentagePromotionService.insert(conn, pourcentagePromotion);
            System.out.println("Insert " + rows + " rows in database");
            // traitement...


            ModelView mv = new ModelView("bo/config.jsp", null);
            fetchData(conn, mv);
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Authorized
    @Post
    @UrlMapping(url = "config_reservation")
    public ModelView minNbHeureReservation(
            @Validate @Param(name = "minNbHeureReservation") MinNbHeureReservation minNbHeureReservation
    ) {
        try (Connection conn = databaseService.getConnection()) {
            // traitement...
            minNbHeureReservation.setDate_modification(LocalDateTime.now());
            int rows = this.minNbHeureReservationService.insert(conn, minNbHeureReservation);
            System.out.println("Insert " + rows + " rows in database");
            // traitement...


            ModelView mv = new ModelView("bo/config.jsp", null);
            fetchData(conn, mv);
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Authorized
    @Post
    @UrlMapping(url = "config_annulation")
    public ModelView minNbHeureAnnulation(
            @Validate @Param(name = "minNbHeureAnnulation") MinNbHeureAnnulation minNbHeureAnnulation
    ) {
        try (Connection conn = databaseService.getConnection()) {
            // traitement...
            minNbHeureAnnulation.setDate_modification(LocalDateTime.now());
            int rows = this.minNbHeureAnnulationService.insert(conn, minNbHeureAnnulation);
            System.out.println("Insert " + rows + " rows in database");
            // traitement...


            ModelView mv = new ModelView("bo/config.jsp", null);
            fetchData(conn, mv);
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
