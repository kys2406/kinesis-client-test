package me.kys2406.kinesiscloud.sync;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.stereotype.Component;

@EnableBinding(Source.class)
@Component
@Slf4j
@RequiredArgsConstructor
public class SyncProducer {

    private final Source source;

    public void sendMessage(String message) {
        log.error("[YS] : " + message);
    }
}
