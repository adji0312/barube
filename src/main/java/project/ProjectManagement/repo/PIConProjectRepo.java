package project.ProjectManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.ProjectManagement.model.PIConProject;
import project.ProjectManagement.model.ProjectPIC;
import project.ProjectManagement.model.User;

import java.util.Optional;

public interface PIConProjectRepo extends JpaRepository<PIConProject, Long> {

    @Query(value = "SELECT u FROM PIConProject u WHERE u.pic_id = :PIConProject")
    Optional<PIConProject> findByPICId(@Param("PIConProject") String PIConProject);
}
