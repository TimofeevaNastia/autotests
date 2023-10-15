package ru.mytest.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//@Getter
//@Accessors(chain = true)
@Configuration
@ConfigurationProperties("config")
@EnableConfigurationProperties({Config.class, MQConfig.class})
@Data
public class Config {
    private final Stand stand = new Stand();
    private final Database database = new Database();
    private final Services services = new Services();

}
