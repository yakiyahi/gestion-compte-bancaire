package com.yakiyahi.ProjectApiRest.apis;

import com.yakiyahi.ProjectApiRest.dao.RoleRepo;
import com.yakiyahi.ProjectApiRest.entities.AppUser;
import com.yakiyahi.ProjectApiRest.entities.UsrRole;
import com.yakiyahi.ProjectApiRest.services.interfaces.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class UserRessource {
    private UserService userService;
    RoleRepo roleRepo;

    public UserRessource(UserService userService, RoleRepo roleRepo) {
        this.userService = userService;
        this.roleRepo = roleRepo;
    }

    //@GetMapping("/allusers")
    @RequestMapping(value = "/allusers",method = RequestMethod.GET)
    public List<AppUser> getUsers(){
        return  userService.getUsers();
    }

    @PostMapping("/allusers/save")
    public ResponseEntity<AppUser> saveUsers(@RequestBody AppUser user){
        URI url = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/save").toUriString());
        return  ResponseEntity.created(url).body(userService.saveUser(user));
    }
    @PutMapping("/allusers/update")
    public ResponseEntity<AppUser> updateUsers(@RequestBody AppUser user){
        URI url = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/update").toUriString());
        return  ResponseEntity.created(url).body(userService.updateUser(user));
    }
    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") String id){
        try {
            Boolean resp = userService.deleteUser(Long.valueOf(id));
            if (resp == true) {
                return "SUCCESS";
            } else {
                return "ERROR";
            }
        }catch (Exception e){
            return "ERROR";
        }

    }
    @GetMapping("/user/search/{mot_cle}")
    public List<AppUser> findUser(@PathVariable("mot_cle") String mot_cle){
        try {

                return userService.findUser("%"+mot_cle+"%");

        }catch (Exception e){
            return new ArrayList<>();
        }

    }
    @RequestMapping(value = "/role/list",method = RequestMethod.GET)
    public List<UsrRole> getRoles(){
        return  roleRepo.findAll();
    }
    @PostMapping("/roles/save")
    public ResponseEntity<UsrRole> saveRole(@RequestBody UsrRole role){
        URI url = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/roles/save").toUriString());
        return  ResponseEntity.created(url).body(userService.saveRole(role));
    }
    @PostMapping("/role/addtouser")
    public ResponseEntity<RoleToUserForm> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(),form.getRolename());
        return  ResponseEntity.ok().build();
    }
    @GetMapping("/role/allrolesname")
    public List<String> allRoleName(){

        return roleRepo.allRoleName();
    }
    @GetMapping("/role/rechercher/{roleName}")
    public UsrRole getRole(@PathVariable("roleName") String roleName){

        return roleRepo.findRoleByName(roleName);
    }
    @DeleteMapping("/role/delete/{id}")
    public String deleteRoler(@PathVariable("id") String id){
        try {
              boolean resp = userService.deleteRole(Long.valueOf(id));
            if (resp == true) {
                return "SUCCESS";
            } else {
                return "ERROR";
            }
        }catch (Exception e){
            return "ERROR";
        }

    }
    @PutMapping("/role/update")
    public ResponseEntity<UsrRole> updateRole(@RequestBody UsrRole role){
        URI url = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/update").toUriString());
        return  ResponseEntity.created(url).body(userService.updateRole(role));
    }
}

@Data
class RoleToUserForm{

    private String username;
    private String rolename;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
