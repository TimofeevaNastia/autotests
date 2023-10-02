package ru.mytest.db;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mytest.configuration.Config;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.util.Optional.ofNullable;

public class DataBaseConf {
    ThreadLocal<DBConnection> connection = new ThreadLocal<>();

    public DataBaseConf(DataSource dataSource) {
//        var db = config.getDatabase().addressServiceDataSource();
        if (ofNullable(connection.get()).isEmpty() || !connection.get().isConnected()) {
            this.connection.set(new DBConnection(dataSource));
        }
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        ResultSet set = connection().createStatement().executeQuery("select * from addresses");
        return set.next() ? set : null;
    }

    private Connection connection() {
        return this.connection.get().connection();
    }
}
