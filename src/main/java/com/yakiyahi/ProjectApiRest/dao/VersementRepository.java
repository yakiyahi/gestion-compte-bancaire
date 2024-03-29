package com.yakiyahi.ProjectApiRest.dao;

import com.yakiyahi.ProjectApiRest.entities.Versement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.sql.Date;
import java.util.List;

@RepositoryRestResource
public interface VersementRepository extends JpaRepository <Versement,Long>{

    @Query("select v from Versement v where  v.client.numCpte like :x  or v.client.nomCli like :x ")
    public List<Versement> rechVers(@Param("x")String motCle);
    @Query("select v from Versement v where  v.dateVers =:x ")
    public List<Versement> rechVersByDate(@Param("x") Date date);

    @Query("select v.id from Versement v ")
    public List<Long> idsVers();

}
