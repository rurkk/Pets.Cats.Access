package pets.cats.data.petscatsaccessservices.cat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.ListenerContainerFactory;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import pets.cats.data.petscatsaccessservices.dto.CatDTO;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class CatServiceImpl implements CatService {

    private final CatDTOModelMapper catDTOModelMapper;
    private final KafkaTemplate<String, CatModel> kafkaTemplate;

    private final CountDownLatch latch = new CountDownLatch(1);
    private CatResponse receivedResponse;

    @Autowired
    public CatServiceImpl(CatDTOModelMapper catDTOModelMapper,
                          KafkaTemplate<String, CatModel> kafkaTemplate,
                          ListenerContainerFactory<MessageListener<String, CatResponse>> factory) {
        this.catDTOModelMapper = catDTOModelMapper;
        this.kafkaTemplate = kafkaTemplate;

        MessageListenerContainer container = factory.createContainer("pets.cats.data.create-cat-response");
        container.setupMessageListener((MessageListener<String, CatResponse>) record -> {
            receivedResponse = record.value();
            latch.countDown();
        });
        container.start();
    }

    @Override
    public CatDTO createCat(CatDTO cat) {
        var catTransfer = catDTOModelMapper.toModel(cat);

        kafkaTemplate.send("pets.cats.data.create-cat", catTransfer);

        try {
            if (latch.await(10, TimeUnit.SECONDS)) {
                return receivedResponse;
            } else {
                throw new RuntimeException("Timeout waiting for response");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted", e);
        }
    }
}