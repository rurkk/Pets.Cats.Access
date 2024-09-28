package pets.cats.data.petscatsaccessservices.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OwnerDTO {
    private UUID id;
    private String name;
    private LocalDate birthDate;
    @Singular @EqualsAndHashCode.Exclude private List<CatDTO> ownedCats;
    private UserDTO user;
}