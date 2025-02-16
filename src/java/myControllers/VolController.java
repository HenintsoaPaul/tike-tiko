package myControllers;

import entity.config.MinNbHeureAnnulation;
import entity.config.MinNbHeureReservation;
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
import java.time.LocalDateTime;

@Controller
public class VolController {

    private final AvionService avionService = new AvionService();
    private final VilleService villeService = new VilleService();

    private final DatabaseService databaseService = new DatabaseService();

    private void fetchData(Connection conn, ModelView mv) {
        mv.addObject("avions", avionService.select(conn, "select * from avion"));
        mv.addObject("villes", villeService.select(conn, "select * from ville"));
    }

    @Get
    @UrlMapping(url = "vol_add")
    public ModelView form() {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("bo/vol/vol_add.jsp", null);
            fetchData(conn, mv);
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
