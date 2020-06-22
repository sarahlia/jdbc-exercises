package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    public List<Ad> all(int id) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE user_id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving your ad.", e);
        }
    }

    @Override
    public Ad findOne(long id) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE id = ? LIMIT 1");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return extractAd(rs);
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException("Error retrieving ad.", e);
        }
    }

    public List<Ad> searchAd(String keyword) {
        try {
            String searchQuery = "SELECT * FROM ads AS a LEFT JOIN ad_category AS ac ON a.id = ac.ad_id JOIN categories as c ON c.id = ac.category_id WHERE (a.description LIKE ? OR a.title LIKE ? OR c.name LIKE ?)";
            String searchTermWithWildcards = "%" + keyword + "%";
            PreparedStatement stmt = connection.prepareStatement(searchQuery);
            stmt.setString(1, searchTermWithWildcards);
            stmt.setString(2, searchTermWithWildcards);
            stmt.setString(3, searchTermWithWildcards);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error in searching the ad", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    @Override
    public Long updateAd(Ad ad, Ad updateAd) {
        try {
            String updateQuery = "UPDATE ads SET user_id = ?, title = ?, description = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, (int) ad.getUserId());
            stmt.setString(2, updateAd.getTitle());
            stmt.setString(3, updateAd.getDescription());
            stmt.setInt(4, (int) ad.getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating an ad.", e);
        }
    }

    @Override
    public Long deleteAd(long id) {
        try {
            String deleteQuery = "DELETE FROM ads WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(deleteQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, id);
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting an ad.", e);
        }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("title"),
                rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
}
