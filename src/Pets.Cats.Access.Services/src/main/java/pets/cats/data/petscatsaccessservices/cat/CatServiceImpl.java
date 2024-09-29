package pets.cats.data.petscatsaccessservices.cat;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Service;
import pets.cats.data.petscatsaccessservices.dto.CatDTO;

import java.util.concurrent.ExecutionException;

@Service
public class CatServiceImpl implements CatService {

    @Value("${pets.cats.data.create-cat}")
    private String requestCreateCatTopic;
    private final CatDTOModelMapper catDTOModelMapper;
    private final ReplyingKafkaTemplate<String, CatCreate, CatResult> replyingKafkaTemplate;

    @Autowired
    public CatServiceImpl(
            CatDTOModelMapper catDTOModelMapper,
            ReplyingKafkaTemplate<String, CatCreate, CatResult> replyingKafkaTemplate) {
        this.catDTOModelMapper = catDTOModelMapper;
        this.replyingKafkaTemplate = replyingKafkaTemplate;
    }

    @Override
    public
    ResponseEntity<CatResult> createCat(CatDTO cat) throws ExecutionException, InterruptedException {
        var catCreate = catDTOModelMapper.toModel(cat);

        ProducerRecord<String, CatCreate> record = new ProducerRecord<>(requestCreateCatTopic, null, catCreate.getId().toString(), catCreate);
        RequestReplyFuture<String, CatCreate, CatResult> future = replyingKafkaTemplate.sendAndReceive(record);
        ConsumerRecord<String, CatResult> response = future.get();

        return new ResponseEntity<>(response.value(), HttpStatus.OK);
    }
}