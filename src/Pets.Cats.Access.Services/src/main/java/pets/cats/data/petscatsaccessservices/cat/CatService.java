package pets.cats.data.petscatsaccessservices.cat;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pets.cats.data.petscatsaccessservices.dto.CatDTO;

import java.util.concurrent.ExecutionException;

@Service
public interface CatService {
    ResponseEntity<CatResult> createCat(CatDTO cat) throws ExecutionException, InterruptedException;
}