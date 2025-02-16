package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DatabaseService {

    public Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/tike_tiko";
        String user = "postgres";
        String password = "itu16";
        return DriverManager.getConnection(url, user, password);
    }

    public <T> List<T> select(Connection conn, String query, Function<ResultSet, T> rowMapper) {
        List<T> results = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                results.add(rowMapper.apply(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
