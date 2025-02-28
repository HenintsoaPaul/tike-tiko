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

    private int getUserRoleLevel(Utilisateur utilisateur) {
        String ref = "admin";
        if (utilisateur.getNom().equals(ref)) {
            return 10;
        }
        return 1;
    }

    @Get
    @UrlMapping(url = "login")
    public ModelView login() {
        return new ModelView("bo/login.jsp", null);
    }

    @Get
    @UrlMapping(url = "logout")
    public String logout() {
        summerSession.destroy();
        return "redirect:GET:/login";
    }

    @Post
    @UrlMapping(url = "login_auth")
    public ModelView handleForm(
            @Validate(errorPage = "login")
            @Param(name = "utilisateur") Utilisateur u
    ) throws Exception {
        try (Connection conn = databaseService.getConnection()) {
            Utilisateur authenticated = this.utilisateurService.authenticate(conn, u);

            // Save authentication in session
            summerSession.addAttribute("isAuthenticated", true);
            summerSession.addAttribute("userRoleLevel", getUserRoleLevel(authenticated));

            // Redirection vers une route protegee
            return new ModelView("redirect:GET:/config", null);
        }
    }
}
