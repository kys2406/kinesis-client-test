package me.kys2406.kcldemo.sync;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.kinesis.exceptions.InvalidStateException;
import software.amazon.kinesis.exceptions.ShutdownException;
import software.amazon.kinesis.lifecycle.events.*;
import software.amazon.kinesis.processor.ShardRecordProcessor;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerProcessor implements ShardRecordProcessor {
    private String shardId;

    @Override
    public void initialize(InitializationInput initializationInput) {
        shardId = initializationInput.shardId();
        log.info("[YS] : SHARD ID : {}", shardId);
        log.info("[YS] : Initializing @ Sequence: {}", initializationInput.extendedSequenceNumber());
    }

    @Override
    public void processRecords(ProcessRecordsInput processRecordsInput) {
        try {
            log.info("[YS] : Processing {} record(s)", processRecordsInput.records().size());
            processRecordsInput.records().parallelStream().forEach(record -> {
                String partitionKey = record.partitionKey();
                byte[] arr = new byte[record.data().remaining()];
                record.data().get(arr);
                String data = new String(arr);

            });
        } catch (Throwable t) {
            log.error("Caught throwable while processing records. Aborting.");
            log.error(t.getMessage(), t);
        } finally {

        }
    }

    @Override
    public void leaseLost(LeaseLostInput leaseLostInput) {
        log.info("Lost lease, so terminating.");
    }

    @Override
    public void shardEnded(ShardEndedInput shardEndedInput) {
        try {
            log.info("Reached shard end checkpointing.");
            shardEndedInput.checkpointer().checkpoint();
        } catch (ShutdownException | InvalidStateException e) {
            log.error("Exception while checkpointing at shard end. Giving up.", e);
        } finally {

        }
    }

    @Override
    public void shutdownRequested(ShutdownRequestedInput shutdownRequestedInput) {
        try {
            log.info("Scheduler is shutting down, checkpointing.");
            shutdownRequestedInput.checkpointer().checkpoint();
        } catch (ShutdownException | InvalidStateException e) {
            log.error("Exception while checkpointing at requested shutdown. Giving up.", e);
        } finally {

        }
    }
}