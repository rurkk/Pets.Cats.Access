package pets.cats.data.petscatsaccessservices.cat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaConfig {

    //@Value("${kafka.group.id}")
    //private String groupId;

    @Value("${pets.cats.data.create-cat-result}")
    private String replyTopic;

    @Bean
    public ReplyingKafkaTemplate<String, CatCreate, CatResult> replyingKafkaTemplate(ProducerFactory<String, CatCreate> pf,
                                                                                ConcurrentKafkaListenerContainerFactory<String, CatResult> factory) {
        ConcurrentMessageListenerContainer<String, CatResult> replyContainer = factory.createContainer(replyTopic);
        replyContainer.getContainerProperties().setMissingTopicsFatal(false);
        return new ReplyingKafkaTemplate<>(pf, replyContainer);
    }

    @Bean
    public KafkaTemplate<String, CatResult> replyTemplate(ProducerFactory<String, CatResult> pf,
                                                       ConcurrentKafkaListenerContainerFactory<String, CatResult> factory) {
        KafkaTemplate<String, CatResult> kafkaTemplate = new KafkaTemplate<>(pf);
        factory.getContainerProperties().setMissingTopicsFatal(false);
        factory.setReplyTemplate(kafkaTemplate);
        return kafkaTemplate;
    }
}