package project.ProjectManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ProjectManagement.domain.Response;
import project.ProjectManagement.model.Role;
import project.ProjectManagement.model.User;
import project.ProjectManagement.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRole();
        return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
    }
//    public List<Role> roleList(){
//        return roleService.getAllRole();
//    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long id) {

        Role role = roleService.getRoleById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        Role newRole = roleService.addRole(role);
        if(newRole != null){
            return new ResponseEntity<>(newRole, HttpStatus.CREATED);
        }
        return null;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable("id") Long id,@RequestBody Role role){
        Role updateRole = roleService.updateRole(id,role);
        return new ResponseEntity<>(updateRole, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable("id") Long id){
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
