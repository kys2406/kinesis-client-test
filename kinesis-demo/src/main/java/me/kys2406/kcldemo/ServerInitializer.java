package me.kys2406.kcldemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kys2406.kcldemo.sync.SyncService;
import me.kys2406.kcldemo.sync.SyncService2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ServerInitializer implements ApplicationRunner {

    private final SyncService syncService;

    private final SyncService2 syncService2;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        syncService.resister();

        syncService2.resister();
//        StopWatch stopWatch = new StopWatch();
//
//        ExecutorService executorService = Executors.newFixedThreadPool(8);
//        executorService.submit(() -> {
//            stopWatch.start("test");
//            IntStream.range(999, 1100).parallel().forEach(value -> {
//                syncService.sendMessage("test", String.valueOf(value));
//            });
//            stopWatch.stop();
//            log.info("[YS]" + stopWatch.prettyPrint());
//        });
    }
}