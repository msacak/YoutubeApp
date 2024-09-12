package org.example.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionProvider implements AutoCloseable{


    private static ConnectionProvider instance;
    private Connection connection;
    private ConnectionProvider(){
        this.connection = DatabaseConnection.getInstance().getConnection();
    }


    public static synchronized ConnectionProvider getInstance() {
        if (instance == null) {
            instance = new ConnectionProvider();
        }
        return instance;
    }


    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        if (connection == null || connection.isClosed()) {

            throw new SQLException("Connection is closed");
        }
        return connection.prepareStatement(sql);
    }

    public Connection getConnection() {
        return connection;
    }


    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
