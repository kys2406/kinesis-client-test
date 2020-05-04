package me.kys2406.kinesiscloud.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KinesisConfig {

    @Value("${cloud.aws.region.static}")
    private String region;

//    @Bean
//    public AmazonKinesis amazonKinesisClient() {
//        return AmazonKinesisClientBuilder.standard()
//                .withCredentials(new DefaultAWSCredentialsProviderChain())
//                .withRegion(region)
//                .build();
//    }

//    @Bean
//    public AmazonDynamoDB amazonDynamoDBClient() {
//        return AmazonDynamoDBClientBuilder.standard()
//                .withCredentials(new DefaultAWSCredentialsProviderChain())
//                .withRegion(region)
//                .build();
//    }
}
