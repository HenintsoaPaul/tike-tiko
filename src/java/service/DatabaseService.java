package service;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DatabaseService {

    public Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5435/tike_tiko";
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

    public <T> int insert(Connection conn, String tableName, T object) {
        Class<?> clazz = object.getClass();
        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        // Extract field names and values using reflection
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true); // Allow access to private fields
            try {
                String fieldName = field.getName();
                Object value = field.get(object);

                // Skip the "id" field (assuming it's auto-increment)
                if (!fieldName.equalsIgnoreCase("id")) {
                    columns.add(fieldName);
                    values.add(value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (columns.isEmpty()) {
            throw new IllegalArgumentException("No fields to insert!");
        }

        // Construct SQL query dynamically
        String columnNames = String.join(", ", columns);
        String placeholders = String.join(", ", columns.stream().map(c -> "?").toArray(String[]::new));
        String query = "INSERT INTO " + tableName + " (" + columnNames + ") VALUES (" + placeholders + ")";

        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            // Set parameters dynamically
            for (int i = 0; i < values.size(); i++) {
                stmt.setObject(i + 1, values.get(i));
            }

            int affectedRows = stmt.executeUpdate();

            // Retrieve generated key (if applicable)
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Return the auto-generated ID
                }
            }
            return -1; // No key generated
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Indicate failure
        }
    }

    public <T> int update(Connection conn, String tableName, T object) {
        Class<?> clazz = object.getClass();
        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        Object idValue = null;

        // Extract field names and values using reflection
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true); // Allow access to private fields
            try {
                String fieldName = field.getName();
                Object value = field.get(object);

                if (value != null) { // Ignore null fields
                    if (fieldName.equalsIgnoreCase("id")) {
                        idValue = value; // Assume "id" is the primary key
                    } else {
                        columns.add(fieldName + " = ?");
                        values.add(value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (columns.isEmpty() || idValue == null) {
            throw new IllegalArgumentException("No fields to update or ID is missing!");
        }

        // Construct SQL query dynamically
        String setClause = String.join(", ", columns);
        String query = "UPDATE " + tableName + " SET " + setClause + " WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Set parameters dynamically
            for (int i = 0; i < values.size(); i++) {
                stmt.setObject(i + 1, values.get(i));
            }
            stmt.setObject(values.size() + 1, idValue); // Set ID as last parameter

            return stmt.executeUpdate(); // Return affected rows
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Indicate failure
        }
    }
}
