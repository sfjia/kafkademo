package com.atguigu.myProduce;

import kafka.Kafka;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @Author 贾
 * @Date 2021/1/320:01
 */
public class MyProducer {


    public static void main(String[] args) {

        Properties props = new Properties();
        //集群地址
        props.put("bootstrap.servers", "192.168.37.144:9092,192.168.37.145:9092,192.168.37.146:9092");
        //应答策略192.168.37.145
        // key value 序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //指定分区
        props.put("partitioner.class","com.atguigu.partiner.MyPartionner");

        Producer<String, String> producer = new KafkaProducer<String ,String>(props);
        for (int i = 0; i < 100; i++) {
            Future<RecordMetadata> test = producer.send(new ProducerRecord<String, String>("test", String.valueOf(i), String.valueOf(i)));
            System.out.println("test = " + test);
        }

        producer.close();

    }

}
