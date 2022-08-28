package com.yakiyahi.ProjectApiRest.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@ToString
public class  AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(length=50)
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<UsrRole> roles ;

    public AppUser() {
        super();
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<UsrRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<UsrRole> roles) {
        this.roles = roles;
    }
}
