package pets.cats.data.petscatsaccessservices.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pets.cats.data.petscatsaccessservices.dto.OwnerDTO;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    public OwnerServiceImpl() { }

    @Override
    public OwnerDTO createOwner(OwnerDTO owner) {

        return owner;
    }
}