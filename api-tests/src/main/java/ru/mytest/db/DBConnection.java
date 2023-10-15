package ru.mytest.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static java.util.Optional.ofNullable;

public class DBConnection {
    private Connection connection;

    public DBConnection(DataSource dataSource) {
        this.closeConnection();
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (ofNullable(this.connection).isPresent() && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            throw new AutotestSqlException(e);
        }
    }

    public boolean isConnected() {
        try {
            return ofNullable(this.connection).isPresent() && !this.connection.isClosed();
        } catch (SQLException e) {
            throw new AutotestSqlException(e);
        }
    }

    public Connection connection() {
        if (isConnected()) {
            return this.connection;
        }
        return null;
    }
}
