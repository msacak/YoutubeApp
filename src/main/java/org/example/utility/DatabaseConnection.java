package org.example.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static org.example.utility.Constants.*;

public class DatabaseConnection {


    private static DatabaseConnection instance;

    public static final String dbName = DB_NAME;
    public static final String url = "jdbc:postgresql://localhost:5432/" + dbName; //todo güzelleştir.
    public static final String username = DB_USERNAME;
    public static final String password = DB_PASSWORD;


    private Connection connection;

    private DatabaseConnection() {
        try {

            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {

            throw new RuntimeException("Veritabanı bağlantısı oluşturulamadı", e);
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }



    public Connection getConnection() {
        try {
            if(connection.isClosed()) {
                connection = ConnectionProvider.getInstance().getConnection();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;


    }
}
