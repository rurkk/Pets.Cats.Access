package pets.cats.data.petscatsaccessservices.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatDTO {
    private UUID id;
    private LocalDate birthDate;
    private String name;
    private String breed;
    private String color;
    private OwnerDTO owner;
    @Singular @EqualsAndHashCode.Exclude private List<CatDTO> friendCats;
}