package pets.cats.data.petscatsaccessservices.cat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.*;

@Data
@Builder
public class CatModel {
    private UUID id;

    private LocalDate birthDate;

    private String name;

    private String breed;

    private String color;

    private UUID ownerId;

    @ToString.Exclude @EqualsAndHashCode.Exclude private List<CatModel> friendCats;

    public CatModel(
            UUID id,
            LocalDate birthDate,
            String name,
            String breed,
            String color,
            UUID ownerId,
            List<CatModel> friendCats) {
        this.id = id;
        this.birthDate = birthDate;
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.ownerId = ownerId;
        this.friendCats = friendCats;
        if (friendCats == null) {
            this.friendCats = new ArrayList<>();
        }
    }
}