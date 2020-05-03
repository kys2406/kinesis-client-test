package me.kys2406.kcldemo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.kinesis.common.KinesisClientUtil;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KinesisConfig {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean(name = "consumerClient")
    public KinesisAsyncClient consumerClient() {
        return KinesisClientUtil.createKinesisAsyncClient(KinesisAsyncClient.builder().region(Region.of(region)));
    }

    @Bean(name = "producerClient")
    public KinesisAsyncClient producerClient() {
        return KinesisClientUtil.createKinesisAsyncClient(KinesisAsyncClient.builder().region(Region.of(region)));
    }

    @Bean
    public DynamoDbAsyncClient dynamoClient() {
        return DynamoDbAsyncClient.builder().region(Region.of(region)).build();
    }

    @Bean
    public CloudWatchAsyncClient cloudWatchClient() {
        return CloudWatchAsyncClient.builder().region(Region.of(region)).build();
    }

}
