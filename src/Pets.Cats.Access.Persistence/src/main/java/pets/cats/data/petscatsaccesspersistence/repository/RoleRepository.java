package pets.cats.data.petscatsaccesspersistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pets.cats.data.petscatsaccesspersistence.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}