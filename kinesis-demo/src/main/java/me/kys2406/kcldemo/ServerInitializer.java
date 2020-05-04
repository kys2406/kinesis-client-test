package me.kys2406.kcldemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kys2406.kcldemo.sync.SyncService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ServerInitializer implements ApplicationRunner {

    private final SyncService syncService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        syncService.resister();

//        ExecutorService executorService = Executors.newFixedThreadPool(8);
//        executorService.submit(() -> {
//            StopWatch stopWatch = new StopWatch();
//            IntStream.range(0, 1000).parallel().forEach(value -> {
//                syncService.sendMessage("test", String.valueOf(value));
//            });
//            stopWatch.stop();
//            log.error("[YS]" + stopWatch.prettyPrint());
//        });
    }
}