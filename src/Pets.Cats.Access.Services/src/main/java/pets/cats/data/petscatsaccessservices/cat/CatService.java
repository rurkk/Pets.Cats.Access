package pets.cats.data.petscatsaccessservices.cat;

import org.springframework.stereotype.Service;
import pets.cats.data.petscatsaccessservices.dto.CatDTO;

@Service
public interface CatService {
    CatDTO createCat(CatDTO cat);
}