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

        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
