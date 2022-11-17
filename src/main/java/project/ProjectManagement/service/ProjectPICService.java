package project.ProjectManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.ProjectManagement.exception.PICNotFoundException;
import project.ProjectManagement.exception.ProjectPICNotFoundException;
import project.ProjectManagement.exception.UserNotFoundException;
import project.ProjectManagement.model.PIConProject;
import project.ProjectManagement.model.ProjectPIC;
import project.ProjectManagement.model.User;
import project.ProjectManagement.repo.PIConProjectRepo;
import project.ProjectManagement.repo.ProjectPICRepo;
import project.ProjectManagement.repo.UserRepo;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class ProjectPICService {

    private final ProjectPICRepo projectPICRepo;
    private final PIConProjectRepo picOnProjectRepo;
    private final UserRepo userRepo;


    @Autowired
    public ProjectPICService(ProjectPICRepo projectPICRepo, PIConProjectRepo picOnProjectRepo, UserRepo userRepo) {
        this.projectPICRepo = projectPICRepo;
        this.picOnProjectRepo = picOnProjectRepo;
        this.userRepo = userRepo;
    }


    public ProjectPIC addPIC(String user_id, String project_code) {

        ProjectPIC projectPIC = projectPICRepo.findProjectPICByPICId(user_id).orElseThrow(() -> new PICNotFoundException("PIC Not Found"));
        if(projectPIC == null){
            //add
            ProjectPIC newPIC = new ProjectPIC();
            User user = userRepo.findByUserID(user_id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
            newPIC.setPic_name(user.getUser_name());
            newPIC.setPic_id(user.getUser_id());
            newPIC.setProject_code(project_code);
            newPIC.setCreated_date(new Date());
            projectPICRepo.save(newPIC);


            PIConProject newPIConProject = new PIConProject();
            newPIConProject.setPic_flag(true);
            newPIConProject.setCreated_at(new Date());
            newPIConProject.setPic_id(user_id);
            picOnProjectRepo.save(newPIConProject);
        }else{
            //update
            PIConProject updatePIConProject = picOnProjectRepo.findByPICId(projectPIC.getPic_id()).orElseThrow(() -> new ProjectPICNotFoundException("PIC Not Found"));
            updatePIConProject.setPic_flag(true);
            picOnProjectRepo.save(updatePIConProject);

        }
            return null;
    }
}
