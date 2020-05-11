package me.kys2406.kinesiscloud.sync;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@EnableBinding(KinesisTest2Sink.class)
@Component
@Slf4j
@RequiredArgsConstructor
public class SyncConsumer2 {

    @StreamListener(KinesisTest2Sink.INPUT)
    public void receiveMessage(String message) {
        log.error("[YS] : " + Integer.valueOf(message));
    }
}
