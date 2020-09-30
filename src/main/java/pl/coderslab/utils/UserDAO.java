package pl.coderslab.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final static String CREATE = "INSERT INTO users (username, email, password) VALUES(?, ?, ?);";
    private final static String READ = "SELECT* FROM users";
    private final static String UPDATE = "UPDATE users SET username=?, email=?, password=? WHERE id = ?;";
    private final static String DELETE = "DELETE FROM users WHERE id=?;";

    public void create(User user) throws SQLException {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement prepStmt = conn.prepareStatement(CREATE)) {
            prepStmt.setString(1, user.getUsername());
            prepStmt.setString(2, user.getEmail());
            prepStmt.setString(3, user.getPassword());
            prepStmt.executeUpdate();
        }
    }

    public List<User> read() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement prepStmt = conn.prepareStatement(READ);) {
            ResultSet resultSet = prepStmt.executeQuery();
            String username, email, password;
            while (resultSet.next()) {
                username = resultSet.getString("username");
                email = resultSet.getString("email");
                password = resultSet.getString("password");
                users.add(new User(username, email, password));
            }
        }
        return users;
    }

    public void update(User user) throws SQLException {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement prepStmt = conn.prepareStatement(UPDATE);) {
            prepStmt.setInt(1, user.getId());
            prepStmt.setString(2, user.getUsername());
            prepStmt.setString(3, user.getEmail());
            prepStmt.setString(3, user.getPassword());
            prepStmt.executeUpdate();
        }
    }

    public void delete(User user) throws SQLException {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement prepStmt = conn.prepareStatement(DELETE);) {
            prepStmt.setInt(1, user.getId());
            prepStmt.executeUpdate();
        }
    }
}
