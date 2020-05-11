package me.kys2406.kinesiscloud.sync;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@EnableBinding(KinesisTestSink.class)
@Component
@Slf4j
@RequiredArgsConstructor
public class SyncConsumer {

    @StreamListener(KinesisTestSink.INPUT)
    public void receiveMessage(String message) {
        log.error("[YS] : " + message);
    }


}
