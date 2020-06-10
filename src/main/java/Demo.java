import java.sql.*;

import com.mysql.cj.jdbc.Driver;


public class Demo {

    public static void main(String[] args) {
        try {
            //Connect to MySQL, use a table from an existing database (codeup_test_db)
            DriverManager.registerDriver(new Driver());
            Connection connection = DriverManager.getConnection(
              "jdbc:mysql://localhost/codeup_test_db?serverTimezone=UTC&useSSL=false",
                    "root",
                    "codeup"
            );

            //a SELECT example
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM albums");

            while(rs.next()) {
                System.out.println(rs.getLong("id"));
                System.out.println(rs.getString("artist"));
                System.out.println(rs.getString("name"));
                System.out.println(rs.getInt("release_date"));
                System.out.println(rs.getDouble("sales"));
                System.out.println(rs.getString("genre"));
                System.out.println("----------------------------------");
            }

            //an INSERT example. Here I'm using 2 parameters for executeUpdate to account for id/GeneratedKeys, but only 1 is actually necessary
            String query = "INSERT INTO albums (artist, name, release_date, sales, genre) VALUES ('Hozier', 'Hozier', 2014, 3.5, 'R&B')";
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            rs = statement.getGeneratedKeys();

            if(rs.next()) {
                System.out.println( "You have inserted a new record! The new id is: " + rs.getLong(1) );
            }

            //an UPDATE example
            long idToUpdate = 32;
            //the command in MySQL should be like this:
//            UPDATE albums SET sales = (sales * 100) WHERE id = 32;
            query = "UPDATE albums SET sales = (sales*100) WHERE id = " + idToUpdate;
            statement.executeUpdate(query);
            System.out.println("id #" + idToUpdate + " has been updated.");

        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
