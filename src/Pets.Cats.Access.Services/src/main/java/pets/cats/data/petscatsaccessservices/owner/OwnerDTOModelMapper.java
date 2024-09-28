package pets.cats.data.petscatsaccessservices.owner;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pets.cats.data.petscatsaccessservices.dto.OwnerDTO;

@Mapper(componentModel = "spring")
public interface OwnerDTOModelMapper {
    @Mapping(target = "ownedCats", qualifiedByName = "ownedCatsDTOMapper")
    OwnerDTO toDTO(OwnerModel model);

    @Mapping(target = "ownedCats", qualifiedByName = "ownedCatsDTOMapper")
    OwnerModel toModel(OwnerDTO model);
}