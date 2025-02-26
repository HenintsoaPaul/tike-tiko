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

    private ModelView getModelView(Connection conn) {
        ModelView mv = new ModelView("bo/config/index.jsp", null);

        mv.addObject("typeSieges", typeSiegeService.selectAll(conn));

        mv.addObject("vPourcentagePromotions", vpPromotionService.selectAll(conn));
        mv.addObject("minNbHeureReservations", minNbHeureReservationService.selectAll(conn));
        mv.addObject("minNbHeureAnnulations", minNbHeureAnnulationService.selectAll(conn));

        return mv;
    }

    @Authorized
    @Get
    @UrlMapping(url = "config")
    public ModelView list() {
        try (Connection conn = databaseService.getConnection()) {
            return getModelView(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Authorized
    @Post
    @UrlMapping(url = "config_pourcentage")
    public ModelView pourcentagePromotion(
            @Validate(errorPage = "config")
            @Param(name = "pourcentagePromotion") PourcentagePromotion pourcentagePromotion
    ) {
        try (Connection conn = databaseService.getConnection()) {
            pourcentagePromotion.setDate_modification(LocalDateTime.now());
            this.pourcentagePromotionService.insert(conn, pourcentagePromotion);

            return new ModelView("redirect:GET:/config", null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Authorized
    @Post
    @UrlMapping(url = "config_reservation")
    public ModelView minNbHeureReservation(
            @Validate(errorPage = "config")
            @Param(name = "minNbHeureReservation") MinNbHeureReservation minNbHeureReservation
    ) {
        try (Connection conn = databaseService.getConnection()) {
            minNbHeureReservation.setDate_modification(LocalDateTime.now());
            this.minNbHeureReservationService.insert(conn, minNbHeureReservation);

            return new ModelView("redirect:GET:/config", null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Authorized
    @Post
    @UrlMapping(url = "config_annulation")
    public ModelView minNbHeureAnnulation(
            @Validate(errorPage = "config")
            @Param(name = "minNbHeureAnnulation") MinNbHeureAnnulation minNbHeureAnnulation
    ) {
        try (Connection conn = databaseService.getConnection()) {
            minNbHeureAnnulation.setDate_modification(LocalDateTime.now());
            this.minNbHeureAnnulationService.insert(conn, minNbHeureAnnulation);

            return new ModelView("redirect:GET:/config", null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
