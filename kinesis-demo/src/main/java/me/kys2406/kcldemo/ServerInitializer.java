package me.kys2406.kcldemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kys2406.kcldemo.sync.SyncService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Component
@Slf4j
@RequiredArgsConstructor
public class ServerInitializer implements ApplicationRunner {

    private final SyncService syncService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
//        syncService.resister();
        StopWatch stopWatch = new StopWatch();

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        executorService.submit(() -> {
            stopWatch.start("test");
            IntStream.range(999, 1100).parallel().forEach(value -> {
                syncService.sendMessage("test", String.valueOf(value));
            });
            stopWatch.stop();
            log.info("[YS]" + stopWatch.prettyPrint());
        });
    }
}