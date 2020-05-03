package me.kys2406.kcldemo.sync;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.kinesis.processor.ShardRecordProcessor;
import software.amazon.kinesis.processor.ShardRecordProcessorFactory;

@Component
@RequiredArgsConstructor
public class ConsumerProcessorFactory implements ShardRecordProcessorFactory {
    private final ConsumerProcessor consumerProcessor;

    public ShardRecordProcessor shardRecordProcessor() {
        return consumerProcessor;
    }
}