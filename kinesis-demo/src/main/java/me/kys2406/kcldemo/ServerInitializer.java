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
//
//            stopWatch.start("syncAllGoodsQa");
//            syncService.syncAllGoodsQa();
//            stopWatch.stop();
//
//            stopWatch.start("syncAllGoodsEstimate");
//            syncService.syncAllGoodsEstimate();
//            stopWatch.stop();
//
//            stopWatch.start("syncAllCarts");
//            syncService.syncAllCarts();
//            stopWatch.stop();
//
//            stopWatch.start("syncAllWishLists");
//            syncService.syncAllWishLists();
//            stopWatch.stop();
//
//
//            stopWatch.start("syncAllGoodsRandAdmins");
//            syncService.syncAllGoodsRandAdmins();
//            stopWatch.stop();
//
//            stopWatch.start("syncAllGoods");
//            syncService.syncAllGoods();
//            stopWatch.stop();
//
//            stopWatch.start("syncAllBrands");
//            syncService.syncAllBrands();
//            stopWatch.stop();
//
//            stopWatch.start("syncAllGoodsOptions");
//            syncService.syncAllGoodsOptions();
//            stopWatch.stop();
//
//            stopWatch.start("syncAllGoodsSummaries");
//            syncService.syncAllGoodsSummaries();
//            stopWatch.stop();
//
//
//
//            stopWatch.start("syncAllOrders");
//            syncService.syncAllOrders();
//            stopWatch.stop();
//            log.error("[YS]" + stopWatch.prettyPrint());
//        });
    }
}