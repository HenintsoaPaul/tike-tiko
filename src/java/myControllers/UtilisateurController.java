package myControllers;

import entity.Utilisateur;
import service.*;
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
    private final DatabaseService databaseService = new DatabaseService();

    @Get
    @UrlMapping(url = "logout")
    public String logout() {
        summerSession.destroy();
        return "redirect:GET:/login";
    }

    // BackOffice
    @Get
    @UrlMapping(url = "login")
    public ModelView login() {
        summerSession.destroy();
        return new ModelView("bo/login.jsp", null);
    }


    @Post
    @UrlMapping(url = "login_auth")
    public String login_auth(
            @Validate(errorPage = "login")
            @Param(name = "utilisateur") Utilisateur u
    ) throws Exception {
        try (Connection conn = databaseService.getConnection()) {
            Utilisateur authenticated = this.utilisateurService.authenticate(conn, u);
            if (authenticated.getAuth_level() == 0) {
                throw new IllegalArgumentException("N'est pas un admin");
            }
            utilisateurService.saveInSession(summerSession, authenticated);
            return "redirect:GET:/config";
        }
    }

    // FrontOffice
    @Get
    @UrlMapping(url = "fo_login")
    public ModelView fo_login() {
        summerSession.destroy();
        return new ModelView("fo/login.jsp", null);
    }

    @Post
    @UrlMapping(url = "fo_login_auth")
    public String fo_login_auth(
            @Validate(errorPage = "fo_login")
            @Param(name = "utilisateur") Utilisateur u
    ) throws Exception {
        try (Connection conn = databaseService.getConnection()) {
            Utilisateur authenticated = this.utilisateurService.authenticate(conn, u);
            utilisateurService.saveInSession(summerSession, authenticated);
            return "redirect:GET:/fo_reservation_list";
        }
    }
}
