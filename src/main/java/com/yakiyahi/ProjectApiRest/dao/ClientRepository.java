package com.yakiyahi.ProjectApiRest.dao;

import com.yakiyahi.ProjectApiRest.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client,String> {
    @Query("select c from Client c order by c.numCpte DESC")
    public List<Client> allClientsOrderByNum();
    @Query("select c from Client c where c.nomCli like :x OR c.numCpte LIKE :x")
    public List<Client> rechClient(@Param("x")String motCle);
    @Query("select c.numCpte from Client c")
    public List<String> numsClients();
}
