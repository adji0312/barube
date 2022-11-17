package project.ProjectManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.ProjectManagement.model.ProjectPIC;
import project.ProjectManagement.service.ProjectPICService;

@RestController
public class ProjectPICController {

    private final ProjectPICService projectPICService;

    public ProjectPICController(ProjectPICService projectPICService) {
        this.projectPICService = projectPICService;
    }

//    @PostMapping("/add")
//    public ResponseEntity<ProjectPIC> addProjectPIC(@PathVariable String user_id, String project_code){
//        ProjectPIC newProjectPIC = projectPICService.addPIC()
//    }
}
