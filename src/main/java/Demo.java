import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

            //

        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
