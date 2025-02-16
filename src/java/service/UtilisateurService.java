package service;

import entity.Utilisateur;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UtilisateurService {

    DatabaseService databaseService = new DatabaseService();

    public Utilisateur authenticate(Connection conn, Utilisateur formUser) throws Exception {
        String query = "select * from utilisateur where nom = '" + formUser.getNom() + "'";
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
                return new Utilisateur(rs.getString("nom"), rs.getString("password"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
