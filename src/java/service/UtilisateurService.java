package service;

import entity.Utilisateur;
import src.summer.beans.SummerSession;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UtilisateurService {

    DatabaseService databaseService = new DatabaseService();

    public Utilisateur authenticate(Connection conn, Utilisateur formUser) throws Exception {
        String query = "select * from utilisateur where email = '" + formUser.getEmail() + "'";
        List<Utilisateur> utilisateurs = this.select(conn, query);

        Utilisateur u = !utilisateurs.isEmpty() ? utilisateurs.get(0) : null;

        if (u == null) {
            throw new Exception("Erreur de login! Utilisateur introuvable.");
        }
        if (!u.getPassword().equals(formUser.getPassword())) {
            throw new Exception("Erreur de login! Mot de passe incorrect.");
        }

        return u;
    }

    public List<Utilisateur> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("password"),
                        rs.getInt("auth_level")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void saveInSession(SummerSession summerSession, Utilisateur authenticated) throws Exception {
        summerSession.addAttribute("isAuthenticated", true);
        summerSession.addAttribute("userRoleLevel", authenticated.getAuth_level());
        summerSession.addAttribute("utilisateur", authenticated);
    }

}
