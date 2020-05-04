package me.kys2406.kinesisspringclouddemo2.sync;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

//@EnableBinding(Source.class)
@Component
@RequiredArgsConstructor
@Slf4j
public class SyncProducer {
//    private final Source source;
//
//    @InboundChannelAdapter(Source.OUTPUT)
//    public String greet() {
//        log.info("invoking greet...");
//        return "hello world " + System.currentTimeMillis();
//    }
//
//    public void sendMessage() {
//        source.output().send(MessageBuilder.withPayload("test").build());
//    }
}
