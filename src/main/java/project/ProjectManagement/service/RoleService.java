package project.ProjectManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import project.UserManagement.exception.RoleNotFoundException;
import project.ProjectManagement.exception.RoleNotFoundException;
import project.ProjectManagement.model.Role;
import project.ProjectManagement.repo.RoleRepo;

//import javax.management.relation.RoleNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private final RoleRepo roleRepo;

    private final UserService userService;

    @Autowired
    public RoleService(RoleRepo roleRepo, UserService userService){
        this.roleRepo = roleRepo;
        this.userService = userService;
    }

    public List<Role> getAllRole(){
        return roleRepo.findAll();
    }

    public Role addRole(Role role){
        role.setCreated_date(new Date());
        role.setCreated_by(this.userService.getUserIdFromContext());
        return roleRepo.save(role);
    }

    public Role getRoleById(Long id) throws RoleNotFoundException {
        return roleRepo.findRoleById(id).orElseThrow(() -> new RoleNotFoundException("Role Not Found"));
    }

    public Role updateRole(Long id, Role role) throws RoleNotFoundException {
        Role updatedRole = roleRepo.findRoleById(id).orElseThrow(() -> new RoleNotFoundException("Role Not Found"));
        updatedRole.setRole_id(role.getRole_id());
        updatedRole.setRole_name(role.getRole_name());
        updatedRole.setRole_desc(role.getRole_desc());
        updatedRole.setModify_date(new Date());
//        role.setModify_by(this.userService.getUserIdFromContext());
        return roleRepo.save(updatedRole);
    }

    public void deleteRole(Long id) throws RoleNotFoundException {
        Role deletedRole = roleRepo.findRoleById(id).orElseThrow(() -> new RoleNotFoundException("Role Not Found"));
        roleRepo.delete(deletedRole);
    }
}
