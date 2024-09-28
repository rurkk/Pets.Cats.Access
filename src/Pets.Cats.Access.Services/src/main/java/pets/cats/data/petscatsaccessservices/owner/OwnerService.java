package pets.cats.data.petscatsaccessservices.owner;

import org.springframework.stereotype.Service;
import pets.cats.data.petscatsaccessservices.dto.OwnerDTO;

@Service
public interface OwnerService {
    OwnerDTO createOwner(OwnerDTO owner);

//    OwnerDTO getOwnerById(UUID id) throws AccessDeniedException;
//
//    List<OwnerDTO> getAllOwners();
//
//    void removeOwner(OwnerDTO owner) throws AccessDeniedException;
//
//    List<CatDTO> findAllOwnedCats(OwnerDTO owner) throws AccessDeniedException;
}