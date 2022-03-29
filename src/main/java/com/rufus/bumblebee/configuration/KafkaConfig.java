package com.rufus.bumblebee.configuration;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Integer.parseInt;
import static java.lang.Short.parseShort;

@Configuration
public class KafkaConfig {

    private final Environment env;

    private static final String KAFKA_SERVER_KEY_PROPERTY = "kafka.producer.server";
    private static final String KAFKA_TOPIC_NAME_PROPERTY = "kafka.topic.name";
    private static final String KAFKA_TOPIC_PARTITION_PROPERTY = "kafka.topic.partition";
    private static final String KAFKA_TOPIC_PARTITION_FACTORY_PROPERTY = "kafka.topic.partition-factory";
    private static final String KAFKA_TOPIC_MESSAGE_SIZE_PROPERTY = "kafka.message.max-size";

    @Autowired
    public KafkaConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(
                AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                env.getProperty(KAFKA_SERVER_KEY_PROPERTY)
        );
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic() {
        NewTopic topic = new NewTopic(
                env.getProperty(KAFKA_TOPIC_NAME_PROPERTY),
                parseInt(Objects.requireNonNull(env.getProperty(KAFKA_TOPIC_PARTITION_PROPERTY))),
                parseShort(Objects.requireNonNull(env.getProperty(KAFKA_TOPIC_PARTITION_FACTORY_PROPERTY)))
        );
        Map<String, String> configs = new HashMap<>();
        configs.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, env.getProperty(KAFKA_TOPIC_MESSAGE_SIZE_PROPERTY));
        topic.configs(configs);
        return topic;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty(KAFKA_SERVER_KEY_PROPERTY));
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, env.getProperty(KAFKA_TOPIC_MESSAGE_SIZE_PROPERTY));
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
