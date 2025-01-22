package stmall.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import stmall.config.kafka.KafkaProcessor;
import stmall.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderModified'"
    )
    public void wheneverOrderModified_ModifyDelivery(
        @Payload OrderModified orderModified
    ) {
        OrderModified event = orderModified;
        System.out.println(
            "\n\n##### listener ModifyDelivery : " + orderModified + "\n\n"
        );

        // Sample Logic //
        Delivery.modifyDelivery(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
