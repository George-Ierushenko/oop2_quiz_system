package quizsystem.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class DatabaseConfig {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                Properties props = new Properties();
                FileInputStream in = new FileInputStream("app/src/main/java/resources/application.properties");
                props.load(in);
                in.close();

                String url = props.getProperty("spring.datasource.url");
                String user = props.getProperty("spring.datasource.username");
                String password = props.getProperty("spring.datasource.password");

                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException | IOException e) {
                System.out.println("Error obtaining database connection: " + e.getMessage());
                e.printStackTrace();
            }

            return connection;
        }
    }
}
