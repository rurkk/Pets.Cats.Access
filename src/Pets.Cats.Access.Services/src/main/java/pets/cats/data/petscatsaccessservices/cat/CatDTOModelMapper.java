package pets.cats.data.petscatsaccessservices.cat;

import org.mapstruct.*;
import pets.cats.data.petscatsaccessservices.dto.CatDTO;

@Mapper(componentModel = "spring")
public interface CatDTOModelMapper {
    @Mapping(target = "friendCats", qualifiedByName = "friendDTOMapper")
    CatCreate toModel(CatDTO model);
}