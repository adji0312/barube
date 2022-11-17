package project.ProjectManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ProjectManagement.model.Role;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findRoleById(Long id);
}
