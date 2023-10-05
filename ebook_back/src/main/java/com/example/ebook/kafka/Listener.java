package com.example.ebook.kafka;

import com.example.ebook.Service.CartService;
import com.example.ebook.WebSocket.WebSocketServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    @Autowired
    CartService cartService;
    @Autowired
    KafkaTemplate kafkaTemplate;
    @Autowired
    WebSocketServer webSocketServer;

    @KafkaListener(topics = "Order",groupId = "group_topic_order")
    public void makeOrderListener(ConsumerRecord<String, String> record) throws JsonProcessingException {

        System.out.println("uid="+record.key()+"  "+record.value());

        String result =cartService.makeOrder(Integer.parseInt(record.key()));

        kafkaTemplate.send("OrderFinished",record.key(),result);

    }

    @KafkaListener(topics = "OrderFinished",groupId = "group_topic_order")
    public void finishOrderListener(ConsumerRecord<String, String> record){
        webSocketServer.sendMessage(record.key(), record.value());
    }

}
