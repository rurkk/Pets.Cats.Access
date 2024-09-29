package pets.cats.data.petscatsaccessservices.cat;

import java.time.LocalDate;
import java.util.UUID;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatResult {
    private UUID id;

    private LocalDate birthDate;

    private String name;

    private String breed;

    private String color;

    private UUID ownerId;
}