package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
