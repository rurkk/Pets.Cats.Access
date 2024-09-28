package pets.cats.data.petscatsaccessservices.owner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.*;

@Data
@Builder
public class OwnerModel {
    private UUID id;

    private String name;

    private LocalDate birthDate;

    private UUID userId;

    public OwnerModel(UUID id, String name, LocalDate birthDate, UUID userId) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.userId = userId;
    }
}