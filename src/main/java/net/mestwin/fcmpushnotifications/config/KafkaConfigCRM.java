package net.mestwin.fcmpushnotifications.config;

import net.mestwin.fcmpushnotifications.dto.CrmDTO;
import net.mestwin.fcmpushnotifications.dto.QuoraDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfigCRM{
    @Bean
    @ConditionalOnMissingBean(name = "CRMKafkaListenerContainerFactory")
    public ConsumerFactory<String, CrmDTO> crmConsumerKafka() {
        JsonDeserializer<CrmDTO> deserializer = new JsonDeserializer<>(CrmDTO.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.20.3:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<String, CrmDTO>(config, new StringDeserializer(), deserializer);
    }
    @Bean(name="CRMKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, CrmDTO> CrmKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CrmDTO> factory = new ConcurrentKafkaListenerContainerFactory<String, CrmDTO>();
        factory.setConsumerFactory(crmConsumerKafka());
        return factory;
    }
}