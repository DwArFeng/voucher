package com.dwarfeng.voucher.impl.handler.pusher;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地 Kafka 推送器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class NativeKafkaPusher extends AbstractPusher {

    public static final String PUSHER_TYPE = "kafka.native";

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${pusher.kafka.native.topic.check_reset}")
    private String checkResetTopic;

    public NativeKafkaPusher(
            @Qualifier("nativeKafkaPusher.kafkaTemplate") KafkaTemplate<String, String> kafkaTemplate
    ) {
        super(PUSHER_TYPE);
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional(transactionManager = "nativeKafkaPusher.kafkaTransactionManager")
    @Override
    public void checkReset() {
        kafkaTemplate.send(checkResetTopic, StringUtils.EMPTY);
    }

    @Override
    public String toString() {
        return "NativeKafkaPusher{" +
                "kafkaTemplate=" + kafkaTemplate +
                ", checkResetTopic='" + checkResetTopic + '\'' +
                ", pusherType='" + pusherType + '\'' +
                '}';
    }

    @Configuration
    public static class KafkaPusherConfiguration {

        private static final Logger LOGGER = LoggerFactory.getLogger(KafkaPusherConfiguration.class);

        @Value("${pusher.kafka.native.bootstrap_servers}")
        private String producerBootstrapServers;
        @Value("${pusher.kafka.native.retries}")
        private int retries;
        @Value("${pusher.kafka.native.linger}")
        private long linger;
        @Value("${pusher.kafka.native.buffer_memory}")
        private long bufferMemory;
        @Value("${pusher.kafka.native.batch_size}")
        private int batchSize;
        @Value("${pusher.kafka.native.acks}")
        private String acks;
        @Value("${pusher.kafka.native.transaction_prefix}")
        private String transactionPrefix;

        @Bean("nativeKafkaPusher.producerProperties")
        public Map<String, Object> producerProperties() {
            LOGGER.info("配置Kafka生产者属性...");
            Map<String, Object> props = new HashMap<>();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerBootstrapServers);
            props.put(ProducerConfig.RETRIES_CONFIG, retries);
            props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
            props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
            props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
            props.put(ProducerConfig.ACKS_CONFIG, acks);
            LOGGER.debug("Kafka生产者属性配置完成...");
            return props;
        }

        @Bean("nativeKafkaPusher.producerFactory")
        public ProducerFactory<String, String> producerFactory() {
            LOGGER.debug("配置 Kafka 生产者工厂...");
            Map<String, Object> properties = producerProperties();
            DefaultKafkaProducerFactory<String, String> factory = new DefaultKafkaProducerFactory<>(properties);
            factory.setTransactionIdPrefix(transactionPrefix);
            factory.setKeySerializer(new StringSerializer());
            factory.setValueSerializer(new StringSerializer());
            LOGGER.debug("Kafka 生产者工厂配置完成");
            return factory;
        }

        @Bean("nativeKafkaPusher.kafkaTemplate")
        public KafkaTemplate<String, String> kafkaTemplate() {
            LOGGER.debug("生成 KafkaTemplate...");
            ProducerFactory<String, String> producerFactory = producerFactory();
            KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory, true);
            LOGGER.debug("KafkaTemplate 生成完成...");
            return kafkaTemplate;
        }

        @Bean("nativeKafkaPusher.kafkaTransactionManager")
        public KafkaTransactionManager<String, String> kafkaTransactionManager() {
            LOGGER.debug("生成 KafkaTransactionManager...");
            ProducerFactory<String, String> producerFactory = producerFactory();
            LOGGER.debug("KafkaTransactionManager 生成完成...");
            return new KafkaTransactionManager<>(producerFactory);
        }
    }
}
