package com.yakiyahi.ProjectApiRest.dao;

import com.yakiyahi.ProjectApiRest.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<AppUser,Long> {
    @Query("select u from AppUser u where u.username = :x")
    public AppUser findByUserName(@Param("x") String userName);

    @Query("select u from AppUser u where u.username LIKE :x ")
    public List<AppUser> searchUser(@Param("x") String mot_cle);

}
