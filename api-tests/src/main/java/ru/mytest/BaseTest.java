package ru.mytest;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.mytest.configuration.Config;
import ru.mytest.configuration.Database;
import ru.mytest.configuration.Services;
import ru.mytest.configuration.Stand;
import ru.mytest.db.AddressServiceDB;

@Data
@Accessors(chain = true)
@ContextConfiguration(classes = {Config.class, Database.class, Services.class, Stand.class, AddressServiceDB.class})
@SpringBootTest
@ActiveProfiles("${env}")
//@SpringBootApplication
public class BaseTest {
//    @Autowired
//    public AddressServiceDBImpl addressServiceDB;

    @Autowired
    protected Config config;
//    @Autowired
//    protected AddressServiceRepository addressServiceRepository;
}
