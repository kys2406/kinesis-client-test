package me.kys2406.kinesisdemo2.sync;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.awssdk.services.kinesis.model.PutRecordRequest;
import software.amazon.kinesis.common.ConfigsBuilder;
import software.amazon.kinesis.common.InitialPositionInStream;
import software.amazon.kinesis.common.InitialPositionInStreamExtended;
import software.amazon.kinesis.coordinator.Scheduler;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyncService {
    private final DynamoDbAsyncClient dynamoDbClient;
    private final CloudWatchAsyncClient cloudWatchClient;
    private final ConsumerProcessorFactory consumerProcessorFactory;

    @Resource(name = "consumerClient")
    private final KinesisAsyncClient consumerClient;

    @Resource(name = "producerClient")
    private final KinesisAsyncClient producerClient;

    @Resource(name = "customExecutor")
    private final ThreadPoolTaskExecutor customExecutor;

    @Value("${kinesis.stream}")
    private String streamName;

    @Value("${kinesis.app-name}")
    private String appName;


    public void resister() {
        ConfigsBuilder configsBuilder = new ConfigsBuilder(
                streamName,
                appName,
                consumerClient,
                dynamoDbClient,
                cloudWatchClient,
                UUID.randomUUID().toString(), consumerProcessorFactory);

        Scheduler scheduler = new Scheduler(
                configsBuilder.checkpointConfig(),
                configsBuilder.coordinatorConfig(),
                configsBuilder.leaseManagementConfig(),
                configsBuilder.lifecycleConfig(),
                configsBuilder.metricsConfig(),
                configsBuilder.processorConfig(),
                configsBuilder.retrievalConfig().initialPositionInStreamExtended(
                        InitialPositionInStreamExtended.newInitialPosition(InitialPositionInStream.TRIM_HORIZON)
                ));

//        Scheduler scheduler = new Scheduler(
//                configsBuilder.checkpointConfig(),
//                configsBuilder.coordinatorConfig(),
//                configsBuilder.leaseManagementConfig(),
//                configsBuilder.lifecycleConfig(),
//                configsBuilder.metricsConfig(),
//                configsBuilder.processorConfig(),
//                configsBuilder.retrievalConfig());

        customExecutor.submit(scheduler);
    }


    public void sendMessage(String key, String data) {
        customExecutor.execute(() -> publishRecord(key, data));
    }

    private void publishRecord(String key, String data) {
        PutRecordRequest request = PutRecordRequest.builder()
                .partitionKey(key)
                .streamName(streamName)
                .data(SdkBytes.fromUtf8String(String.valueOf(data)))
                .build();
        try {
            producerClient.putRecord(request).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
