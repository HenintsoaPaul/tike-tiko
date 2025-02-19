package myControllers;

import entity.Utilisateur;
import service.*;
import service.config.MinNbHeureAnnulationService;
import service.config.MinNbHeureReservationService;
import service.config.PourcentagePromotionService;
import src.summer.annotations.Param;
import src.summer.annotations.Validate;
import src.summer.annotations.controller.Controller;
import src.summer.annotations.controller.UrlMapping;
import src.summer.annotations.controller.verb.Get;
import src.summer.annotations.controller.verb.Post;
import src.summer.beans.ModelView;
import src.summer.beans.SummerSession;

import java.sql.Connection;

@Controller
public class UtilisateurController {

    SummerSession summerSession;

    private final VPourcentagePromotionService vpPromotionService = new VPourcentagePromotionService();
    private final PourcentagePromotionService pourcentagePromotionService = new PourcentagePromotionService();
    private final MinNbHeureReservationService minNbHeureReservationService = new MinNbHeureReservationService();
    private final MinNbHeureAnnulationService minNbHeureAnnulationService = new MinNbHeureAnnulationService();

    private final TypeSiegeService typeSiegeService = new TypeSiegeService();
    private final UtilisateurService utilisateurService = new UtilisateurService();
    private final ConfigService configService = new ConfigService();
    private final DatabaseService databaseService = new DatabaseService();

    private int getUserRoleLevel(Utilisateur utilisateur) {
        String ref = "admin";
        if (utilisateur.getNom().equals(ref)) {
            return 10;
        }
        return 1;
    }

    @Get
    @UrlMapping(url = "login")
    public ModelView gotoForm() {
        return new ModelView("bo/login.jsp", null);
    }

    @Get
    @UrlMapping(url = "logout")
    public ModelView logout() {
        summerSession.destroy();

        return new ModelView("bo/login.jsp", null);
    }

    @Post
    @UrlMapping(url = "login_auth")
    public ModelView handleForm(
            @Validate @Param(name = "utilisateur") Utilisateur u
    ) throws Exception {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("bo/config.jsp", null);
            mv.setErrorUrl("login");

            Utilisateur authenticated = this.utilisateurService.authenticate(conn, u);
            System.out.println("Utilisateur confirme");

            // Save authentication in session
            System.out.println("Set session...");
            summerSession.addAttribute("isAuthenticated", true);
            summerSession.addAttribute("userRoleLevel", getUserRoleLevel(authenticated));

            // Redirection vers une route protegee
            fetchData(conn, mv);
            return mv;
        }
    }

    private void fetchData(Connection conn, ModelView mv) {
        mv.addObject("typeSieges", typeSiegeService.select(conn, "select * from type_siege"));

        mv.addObject("vPourcentagePromotions", vpPromotionService.select(conn, "select * from v_pourcentage_promotion order by id desc"));
        mv.addObject("minNbHeureReservations", minNbHeureReservationService.select(conn, "select * from min_nb_heure_reservation order by id desc"));
        mv.addObject("minNbHeureAnnulations", minNbHeureAnnulationService.select(conn, "select * from min_nb_heure_annulation order by id desc"));
    }
}
