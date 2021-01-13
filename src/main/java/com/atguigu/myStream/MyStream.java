package com.atguigu.myStream;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorSupplier;

import java.util.Properties;

/**
 * @Author 贾
 * @Date 2021/1/1321:04
 */
public class MyStream {

    public static void main(String[] args) {

        Properties props = new Properties();
        //集群地址
        props.put("bootstrap.servers", "192.168.37.144:9092,192.168.37.145:9092,192.168.37.146:9092");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG,"logProcessor");


        Topology topology = new Topology();
        topology.addSource("SOURCE","test")
                .addProcessor("PROCESS", new ProcessorSupplier<byte[],byte[]>() {

                    @Override
                    public Processor get() {
                        return new LogProcessor();
                    }
                },"SOURCE")
                .addSink("SINK","test2","PROCESS");

        //创建 kafka Stream
        KafkaStreams kafkaStreams = new KafkaStreams(topology, props);

    }
}
