import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.compat.jms.internal.JMSC;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mytest.BaseTest;
import ru.mytest.api.http.dto.addressservice.AddressRequestDto;
import ru.mytest.api.http.service.AddressService;
import ru.mytest.api.mq.BaseMQService;
import ru.mytest.api.mq.ConcreteMQ;
import ru.mytest.configuration.MQConfig;

import javax.jms.*;
import java.util.concurrent.TimeUnit;


@Slf4j
@DisplayName("Тест addresservice")
//@ContextConfiguration(classes = {AddressServiceDB.class})
public class RunTest extends BaseTest {

//    @Tag("addresservice")
    @Test
    public void positive() {
        log.info("Конфиг: " + getConfig().getStand().getUrl());
    }

    @Test
    public void positive2() {
        log.info("Конфиг: " + getConfig().getStand().getUrl());
    }

    //    @Test
//    public void test1() {
////        throw new RuntimeException("Ошибка");
////        System.out.println(1);
////        addressServiceRepository.findAll();
////        addressServiceDB= new AddressServiceDBImpl(getConfig());
//        log.info("Конфиг: " + getConfig().getStand().getUrl());
//        log.info(new AddressServiceDB(getConfig()).getCity());
//
//        Allure.description("Описание теста ...");
//        Allure.parameter("Параметр 1", "Значение 1");
////        var db = getConfig().getDatabase().addressServiceDataSource();
////        try (var connection = db.getConnection()) {
////            var result = connection.createStatement().executeQuery("select * from addresses");
////            if (result.next()) {
////                var city = result.getString("city");
////                log.info("БД: " + city);
////            }
////        } catch (SQLException throwables) {
////            throwables.printStackTrace();
////        }
////        log.info("БД: " + addressServiceDB.getAllAddress());
////        addressServiceDB.getAllAddress();
////        System.out.println(addressServiceDB.getAllAddress());
//
//    }
//
    @Test
    public void testApi() {
//        given()  //вынесены повторяющиеся спецификация для post запроса
//                .baseUri("URI") //адрес, куда отправляется запрос
//                .basePath("basePath")
//                .contentType(ContentType.JSON).body("user")
//                .when()
//                .post()
//                .then()
//                .statusCode(200)
//                .body("type", equalTo("unknown"));
        var resp = new AddressService().getAddress(new AddressRequestDto().setAddressString("адрес"));
        Awaitility.await().atMost(10, TimeUnit.SECONDS)
                .pollInterval(2, TimeUnit.SECONDS)
                .until(() -> resp != null);
    }

//    @Test
//    public void testMQImpl() {
//        try {
//            /*MQ Configuration*/
//            MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
//            mqQueueConnectionFactory.setHostName("localhost");
//            mqQueueConnectionFactory.setChannel("MQ.CHANNEL");//communications link
//            mqQueueConnectionFactory.setPort(1416);
//            mqQueueConnectionFactory.setQueueManager("QUEUE.MGR");//service provider
//            mqQueueConnectionFactory.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
//
//            /*Create Connection */
//            QueueConnection queueConnection = mqQueueConnectionFactory.createQueueConnection();
//            queueConnection.start();
//
//            /*Create session */
//            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
//
//            /*Create response queue */
//            Queue queue = queueSession.createQueue("QUEUE.RESPONSE");
//
//
//            /*Create text message */
//            TextMessage textMessage = queueSession.createTextMessage("put some message here");
//            textMessage.setJMSReplyTo(queue);
//            textMessage.setJMSType("mcd://xmlns");//message type
//            textMessage.setJMSExpiration(2*1000);//message expiration
//            textMessage.setJMSDeliveryMode(DeliveryMode.PERSISTENT); //message delivery mode either persistent or non-persistemnt
//
//            /*Create sender queue */
//            QueueSender queueSender = queueSession.createSender(queueSession.createQueue("QUEUE.REQEST"));
//            queueSender.setTimeToLive(2*1000);
//            queueSender.send(textMessage);
//
//            /*After sending a message we get message id */
//            System.out.println("after sending a message we get message id "+ textMessage.getJMSMessageID());
//            String jmsCorrelationID = " JMSCorrelationID = '" + textMessage.getJMSMessageID() + "'";
//
//
//            /*Within the session we have to create queue reciver */
//            QueueReceiver queueReceiver = queueSession.createReceiver(queue,jmsCorrelationID);
//
//
//            /*Receive the message from*/
//            Message message = queueReceiver.receive(60*1000);
//            String responseMsg = ((TextMessage) message).getText();
//
//            System.out.println("receive message "+ responseMsg);
//
//            queueSender.close();
//            queueReceiver.close();
//            queueSession.close();
//            queueConnection.close();
//
//
//        } catch (JMSException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Tag("addresservice")
    @Test
    public void testMQ(@Autowired MQConfig mqConfig) throws JMSException {
//        log.info("Конфиг: " + getConfig().getStand().getUrl());
        log.info("Конфиг: " + mqConfig.getMq1().getQueue1());
//        new ConcreteMQ(getMqConfig().getMq1()).sendMessage("");

    }

}