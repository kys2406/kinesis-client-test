package me.kys2406.clientdemo;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class S3Runner implements ApplicationRunner {

    private final AmazonS3 amazonS3;

    private final ObjectMapper objectMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("TEST");

        ListObjectsRequest listObject = new ListObjectsRequest();
        listObject.setBucketName("musinsa.data");
        listObject.setPrefix("data/merged/order_activity_json");

        ObjectListing objectListing = null;
        do {
            objectListing = amazonS3.listObjects(listObject);
            List<String> objectKeys = objectListing.getObjectSummaries().parallelStream()
                    .map(S3ObjectSummary::getKey)
                    .filter(key -> (false == StringUtils.endsWithIgnoreCase(key, "_SUCCESS")))
                    .collect(Collectors.toList());

            log.error("[YS] : " + objectKeys.size());
            log.error(objectKeys.toString());

//            objectKeys.parallelStream().forEach(key -> {
//                S3Object fullObject = amazonS3.getObject("musinsa.data", key);
//                try (GZIPInputStream inputStream = new GZIPInputStream(fullObject.getObjectContent())) {
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                    log.error("" + bufferedReader.lines().count());
//                    bufferedReader.lines().forEach(this::parseJsonNode);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });

            listObject.setMarker(objectListing.getNextMarker());


        } while (objectListing.isTruncated());

        stopWatch.stop();
        log.error(stopWatch.prettyPrint());
    }

    private void parseJsonNode(String value) {
        try {
            JsonNode jsonNode = objectMapper.readTree(value);
//            System.out.println(jsonNode.toPrettyString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
