package com.yakiyahi.ProjectApiRest.services.implement;

import com.yakiyahi.ProjectApiRest.dao.RoleRepo;
import com.yakiyahi.ProjectApiRest.dao.UserRepo;
import com.yakiyahi.ProjectApiRest.entities.AppUser;
import com.yakiyahi.ProjectApiRest.entities.UsrRole;
import com.yakiyahi.ProjectApiRest.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service  @Transactional
public class UserServiceImpl implements UserService ,UserDetailsService{

    public UserRepo userRepo;
    public RoleRepo roleRepo;
    public PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser saveUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public UsrRole saveRole(UsrRole role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppUser user = userRepo.findByUserName(userName);
        UsrRole role = roleRepo.findRoleByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public AppUser getUser(String userName) {
        return userRepo.findByUserName(userName);
    }

    @Override
    public List<AppUser> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public AppUser updateUser(AppUser user) {
        return userRepo.save(user) ;
    }

    @Override
    public UsrRole updateRole(UsrRole role) {
        return roleRepo.save(role);
    }

    @Override
    public boolean deleteUser(Long id) {
        try{
            userRepo.deleteById(id);
            return true;

        }catch (Exception e){

            return false;
        }

    }

    @Override
    public boolean deleteRole(Long id) {
        try{
            roleRepo.deleteById(id);
            return true;

        }catch (Exception e){

            return false;
        }
    }

    @Override
    public List<AppUser> findUser(String mot_cle) {
        return userRepo.searchUser(mot_cle);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByUserName(username);

        if(user==null){
            System.out.println("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        }else{
            System.out.println("User found in database :"+username);
        }
        Collection<SimpleGrantedAuthority> authoritie = new ArrayList<>();
        user.getRoles().forEach(role ->{
            authoritie.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new User(user.getUsername(),user.getPassword(),authoritie);
    }
}
