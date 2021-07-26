package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionNext {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "deletelog";
        String databaseUser = "root";
        String databasePassword = "4562";

        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }
}
