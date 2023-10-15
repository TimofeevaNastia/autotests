package ru.mytest;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.mytest.configuration.*;
import ru.mytest.db.AddressServiceDB;
@Data
@Accessors(chain = true)
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {Config.class, Database.class, Services.class, Stand.class})
@SpringBootTest
@SpringJUnitConfig(classes = {Config.class, Database.class, Services.class, Stand.class})
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
