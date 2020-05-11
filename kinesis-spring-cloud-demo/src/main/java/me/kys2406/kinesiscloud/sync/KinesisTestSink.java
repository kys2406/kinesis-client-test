package me.kys2406.kinesiscloud.sync;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface KinesisTestSink {

    /**
     * Input channel name.
     */
    String INPUT = "kinesis_test";

    /**
     * @return input channel.
     */
    @Input(KinesisTestSink.INPUT)
    SubscribableChannel input();
}
