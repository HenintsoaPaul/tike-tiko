package myControllers;

import entity.Utilisateur;
import service.ConfigService;
import service.DatabaseService;
import service.UtilisateurService;
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
            mv.addObject("pourcentagePromotions", configService.selectPourcentagePromotion(conn, "select * from pourcentage_promotion"));
            mv.addObject("minNbHeureReservations", configService.selectMinNbHeureReservation(conn, "select * from min_nb_heure_reservation"));
            mv.addObject("minNbHeureAnnulations", configService.selectMinNbHeureAnnulation(conn, "select * from min_nb_heure_annulation"));
            return mv;
        }
    }
}
