package com.yakiyahi.ProjectApiRest.services.interfaces;

import com.yakiyahi.ProjectApiRest.entities.AppUser;
import com.yakiyahi.ProjectApiRest.entities.UsrRole;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    UsrRole saveRole(UsrRole role);
    void addRoleToUser(String userName,String roleName);
    AppUser getUser(String userName);
    List<AppUser> getUsers();

    AppUser updateUser(AppUser user);
    UsrRole updateRole(UsrRole role);
    boolean deleteUser(Long id);
    boolean deleteRole(Long id);

    List<AppUser> findUser(String mot_cle);
}
