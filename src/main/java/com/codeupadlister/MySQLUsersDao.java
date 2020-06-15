package com.codeupadlister;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLUsersDao implements Users {
//    private Connection connection;
//
//    public MySQLUsersDao(Config config) {
//        try {
//            DriverManager.registerDriver(new Driver());
//            connection = DriverManager.getConnection(
//                    config.getUrl(),
//                    config.getUser(),
//                    config.getPassword()
//            );
//        } catch (SQLException e) {
//            throw new RuntimeException("Error finding a user by username", e);
//        }
//
//    }

    //able to find 1 specific ad that is currently hard-coded
    public Ad findOne(long id) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE id = 10");
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return extractAd(rs);
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException("Error retrieving ad.", e);
        }
    }

    //    @Override
//    public Ad findOne (long id) throws IndexOutOfBoundsException {
//        return ads.get( (int) id - 1);
//    }
//
//    @Override
//    public User findByUsername(String username) {
//        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
//
//        try {
//            PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setString(1, username);
//            return extractUser(stmt.executeQuery());
//        } catch (SQLException e) {
//            throw new RuntimeException("Error finding a user by username", e);
//        }
//    }
//
//    @Override
//    public Long insert(User user) {
//        String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
//
//        try {
//            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            stmt.setString(1, user.getUsername());
//            stmt.setString(2, user.getEmail());
//            stmt.setString(3, user.getPassword());
//            stmt.executeUpdate();
//
//            ResultSet rs = stmt.getGeneratedKeys();
//            rs.next();
//            return rs.getLong(1);
//        } catch (SQLException e) {
//            throw new RuntimeException("Error creating a new user", e);
//        }
//    }
//
//    private User extractUser(ResultSet rs) throws SQLException {
//        if(! rs.next()) {
//            return null;
//        }
//        return new User(
//                rs.getLong("id"),
//                rs.getString("username"),
//                rs.getString("email"),
//                rs.getString("password")
//        );
//    }
}
