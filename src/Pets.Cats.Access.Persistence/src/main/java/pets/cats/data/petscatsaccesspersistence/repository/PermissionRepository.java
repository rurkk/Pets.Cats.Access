package pets.cats.data.petscatsaccesspersistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pets.cats.data.petscatsaccesspersistence.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Optional<Permission> findByName(String name);
}