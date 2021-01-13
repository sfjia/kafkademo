package com.atguigu.myStream;

import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

import java.nio.charset.Charset;

/**
 * @Author 贾
 * @Date 2021/1/1321:21
 */
public class LogProcessor implements Processor<byte[], byte[]> {

    private ProcessorContext processorContext =null;

    @Override
    public void init(ProcessorContext processorContext) {
       this.processorContext = processorContext;
    }

    @Override
    public void process(byte[] key, byte[] value) {
       String newValue =  new String(value, Charset.defaultCharset());

        if(newValue.contains(">>>")){
            String[] split = newValue.split(">>>");
            newValue=split[1];
        }
        processorContext.forward(key,newValue.getBytes());
    }

    @Override
    public void close() {

    }
}
