import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mytest.BaseTest;
import ru.mytest.db.AddressServiceDB;

@Tag("addresservice")
@Slf4j
@ExtendWith(SpringExtension.class)
@DisplayName("Тест addresservice")
public class RunTest extends BaseTest {

    @Test
    public void test1() {
//        throw new RuntimeException("Ошибка");
//        System.out.println(1);
//        addressServiceRepository.findAll();
//        addressServiceDB= new AddressServiceDBImpl(getConfig());
        log.info("Конфиг: " + getConfig().getStand().getUrl());
        log.info(new AddressServiceDB(getConfig()).getCity());

        Allure.description("Описание теста ...");
        Allure.parameter("Параметр 1", "Значение 1");
//        var db = getConfig().getDatabase().addressServiceDataSource();
//        try (var connection = db.getConnection()) {
//            var result = connection.createStatement().executeQuery("select * from addresses");
//            if (result.next()) {
//                var city = result.getString("city");
//                log.info("БД: " + city);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        log.info("БД: " + addressServiceDB.getAllAddress());
//        addressServiceDB.getAllAddress();
//        System.out.println(addressServiceDB.getAllAddress());

    }


}
