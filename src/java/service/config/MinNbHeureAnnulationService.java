package service.config;

import entity.config.MinNbHeureAnnulation;
import service.DatabaseService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MinNbHeureAnnulationService {

    private final DatabaseService databaseService = new DatabaseService();

    public List<MinNbHeureAnnulation> select(Connection conn, String query) {
        return this.databaseService.select(conn, query, rs -> {
            try {
                return new MinNbHeureAnnulation(
                        rs.getInt("id"),
                        rs.getDouble("val"),
                        rs.getTimestamp("date_modification").toLocalDateTime()
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<MinNbHeureAnnulation> selectAll(Connection conn) {
        return select(conn, "select * from min_nb_heure_annulation order by id desc");
    }

    public MinNbHeureAnnulation selectCurrent(Connection conn) {
        String query = "select * from min_nb_heure_annulation order by id desc limit 1";
        return this.select(conn, query).get(0);
    }

    public int insert(Connection conn, MinNbHeureAnnulation minNbHeureAnnulation) {
        return this.databaseService.insert(conn, "min_nb_heure_annulation", minNbHeureAnnulation);
    }
}
