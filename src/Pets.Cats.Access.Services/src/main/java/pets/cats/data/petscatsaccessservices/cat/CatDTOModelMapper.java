package pets.cats.data.petscatsaccessservices.cat;

import org.mapstruct.*;
import pets.cats.data.petscatsaccessservices.dto.CatDTO;

@Mapper(componentModel = "spring")
public interface CatDTOModelMapper {
    @Mapping(target = "friendCats", qualifiedByName = "friendDTOMapper")
    CatDTO toDTO(CatModel model);

    @Mapping(target = "friendCats", qualifiedByName = "friendDTOMapper")
    CatModel toModel(CatDTO model);
}