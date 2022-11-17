package project.ProjectManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.ProjectManagement.exception.BacklogDevelopmentException;
import project.ProjectManagement.exception.ProjectNotFoundException;
import project.ProjectManagement.exception.RoleNotFoundException;
import project.ProjectManagement.model.BacklogDevelopment;
import project.ProjectManagement.model.Project;
import project.ProjectManagement.repo.BacklogDevelopmentRepo;
import project.ProjectManagement.repo.ProjectRepo;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class ProjectService {
    private final ProjectRepo projectRepo;
    private final BacklogDevelopmentRepo backlogDevelopmentRepo;

    @Autowired
    public ProjectService(ProjectRepo projectRepo, BacklogDevelopmentRepo backlogDevelopmentRepo) {
        this.projectRepo = projectRepo;
        this.backlogDevelopmentRepo = backlogDevelopmentRepo;
    }

    public List<Project> getAllProject(){
        return projectRepo.findAll();
    }

    public Project addProject(Project project){
        Random rnd = new Random();


        String code = project.getApplication()+rnd.nextInt(9999999);

        project.setProject_code(code);
        project.setProject_kickoff(project.getProject_kickoff());
        project.setCreated_date(new Date());

        BacklogDevelopment backlogDevelopment = new BacklogDevelopment();
        backlogDevelopment.setApplication(project.getApplication());
        backlogDevelopment.setBacklog_type(project.getProject_type());
        backlogDevelopment.setBacklog_code(code);
        backlogDevelopment.setBacklog_bpro(project.getProject_bpro());
        backlogDevelopment.setBacklog_desc(project.getProject_desc());
        backlogDevelopment.setBacklog_kickoff(project.getProject_kickoff());
        backlogDevelopment.setBacklog_status(project.getProject_status());
        backlogDevelopment.setCreated_date(new Date());

        backlogDevelopmentRepo.save(backlogDevelopment);
        return projectRepo.save(project);
    }

    public Project getProjectById(Long id) throws RoleNotFoundException {
        return projectRepo.findProjectById(id).orElseThrow(() -> new ProjectNotFoundException("Project Not Found"));
    }

    public Project updateProject(Long id, Project project) throws ProjectNotFoundException,BacklogDevelopmentException {
        Project updatedProject = projectRepo.findProjectById(id).orElseThrow(() -> new ProjectNotFoundException("Project Not Found"));



        updatedProject.setApplication(project.getApplication());
        Random rnd = new Random();
        String code = project.getApplication()+rnd.nextInt(9999999);
        updatedProject.setProject_code(code);
        updatedProject.setProject_type(project.getProject_type());
        updatedProject.setProject_bpro(project.getProject_bpro());
        updatedProject.setProject_status(project.getProject_status());
        updatedProject.setProject_desc(project.getProject_desc());
        updatedProject.setProject_kickoff(project.getProject_kickoff());
        updatedProject.setModify_date(new Date());

        BacklogDevelopment updatedBacklogDev = backlogDevelopmentRepo.findByBacklog_code(updatedProject.getProject_code()).orElseThrow(() -> new BacklogDevelopmentException("Backlog Development Not Found"));

        updatedBacklogDev.setApplication(project.getApplication());
        updatedBacklogDev.setBacklog_code(project.getProject_code());
        updatedBacklogDev.setBacklog_type(project.getProject_type());
        updatedBacklogDev.setBacklog_bpro(project.getProject_bpro());
        updatedBacklogDev.setBacklog_status(project.getProject_status());
        updatedBacklogDev.setBacklog_kickoff(project.getProject_kickoff());
        updatedBacklogDev.setBacklog_desc(project.getProject_desc());
        updatedBacklogDev.setModify_date(new Date());

        backlogDevelopmentRepo.save(updatedBacklogDev);

        return projectRepo.save(updatedProject);
    }

    public void deleteProject(Long id) throws ProjectNotFoundException {
        Project deletedProject = projectRepo.findProjectById(id).orElseThrow(() -> new ProjectNotFoundException("Project Not Found"));
        projectRepo.delete(deletedProject);
    }
}
