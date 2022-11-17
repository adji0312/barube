package project.ProjectManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.ProjectManagement.model.ProjectPIC;
import project.ProjectManagement.model.User;

import java.util.Optional;

public interface ProjectPICRepo extends JpaRepository<ProjectPIC, Long> {

    @Query(value = "SELECT u FROM ProjectPIC u WHERE u.pic_id = :picID")
    Optional<ProjectPIC> findProjectPICByPICId(@Param("picID") String picID);
}
