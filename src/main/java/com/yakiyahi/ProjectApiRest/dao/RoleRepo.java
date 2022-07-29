package com.yakiyahi.ProjectApiRest.dao;

import com.yakiyahi.ProjectApiRest.entities.UsrRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.websocket.server.PathParam;
import java.util.List;

public interface RoleRepo extends JpaRepository<UsrRole,Long> {
    @Query("select r from UsrRole r where r.roleName = :x")
    public UsrRole findRoleByName(@Param("x") String roleName);

    @Query("select r.roleName from UsrRole r ")
    public List<String> allRoleName();

}
