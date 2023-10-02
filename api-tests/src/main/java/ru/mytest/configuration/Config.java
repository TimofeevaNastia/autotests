package ru.mytest.configuration;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Getter
@Accessors(chain = true)
@ConfigurationProperties("config")
@EnableConfigurationProperties(Config.class)
public class Config {
    private final Stand stand = new Stand();
    private final Database database = new Database();
    private final Services services = new Services();

}
