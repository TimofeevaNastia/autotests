package ru.mytest.db;

import io.qameta.allure.Step;
import org.springframework.stereotype.Component;
import ru.mytest.configuration.Config;

import java.sql.SQLException;

@Component
public class AddressServiceDB extends DataBaseConf {
//    private JdbcTemplate jdbcTemplate;
//    private final Config config;

    public AddressServiceDB(Config config) {
        super(config.getDatabase().addressServiceDataSource());
//        this.config = config;
//        this.jdbcTemplate = new JdbcTemplate(config.getDatabase().addressServiceDataSource());

    }

    @Step("Получаем город из БД addresservice")
    public String getCity() {
        try {
            var resultSet = executeQuery("select * from addresses");
            return resultSet.getString("city");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
//        return this.jdbcTemplate.queryForRowSet("select * from addresses").getString(2);
    }
}
