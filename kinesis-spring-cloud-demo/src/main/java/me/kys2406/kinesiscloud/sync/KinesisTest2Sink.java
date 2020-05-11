package me.kys2406.kinesiscloud.sync;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface KinesisTest2Sink {

    /**
     * Input channel name.
     */
    String INPUT = "kinesis_test2";

    /**
     * @return input channel.
     */
    @Input(KinesisTest2Sink.INPUT)
    SubscribableChannel input();
}
