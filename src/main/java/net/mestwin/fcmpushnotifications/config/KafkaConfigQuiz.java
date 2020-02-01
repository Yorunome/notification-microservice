package net.mestwin.fcmpushnotifications.config;

import net.mestwin.fcmpushnotifications.dto.QuizDTO;
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

@EnableKafka
@Configuration
public class KafkaConfigQuiz {

    @Bean
    @ConditionalOnMissingBean(name = "QuizKafkaListenerContainerFactory")
    public ConsumerFactory<String, QuizDTO> quizConsumerKafka() {
        JsonDeserializer<QuizDTO> deserializer = new JsonDeserializer<>(QuizDTO.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.20.131:9092");
        //config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        //config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<String, QuizDTO>(config, new StringDeserializer(), deserializer);
    }
    @Bean(name="QuizKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, QuizDTO> QuizKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, QuizDTO> factory = new ConcurrentKafkaListenerContainerFactory<String, QuizDTO> ();
        factory.setConsumerFactory(quizConsumerKafka());
        return factory;
    }

}
