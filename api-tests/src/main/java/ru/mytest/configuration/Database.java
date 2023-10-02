package ru.mytest.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Optional;

@ConfigurationProperties("config.database")
@EnableConfigurationProperties(Database.class)
public class Database extends HashMap<String, Database.Settings> {

    public DataSource addressServiceDataSource() {
        var settings = Optional.ofNullable(get("addressservice"))
                .orElseThrow(() -> new NullPointerException("Not found settings for 'addressservice'"));
        return dataSource(settings);
    }


    private DataSource dataSource(Settings settings) {
        var result = new DriverManagerDataSource(settings.getUrl(), settings.getLogin(), settings.getPassword());
        result.setDriverClassName(settings.getDriver());
        return result;
    }

    public static class Settings extends HashMap<String, Object> {
        public String getLogin() {
            return extract("login");
        }

        public String getPassword() {
            return extract("password");
        }

        public String getUrl() {
            return extract("url");
        }

        public String getDriver() {
            return extract("driver");
        }

        private String extract(String key) {
            Optional<Object> object = Optional.ofNullable(get(key));
            return (String) (object.isEmpty() || object.get().toString().isEmpty() ? null : object.get());

        }

    }
}
