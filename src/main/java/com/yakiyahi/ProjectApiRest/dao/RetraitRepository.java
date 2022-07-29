package com.yakiyahi.ProjectApiRest.dao;

import com.yakiyahi.ProjectApiRest.entities.Retrait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RetraitRepository extends JpaRepository<Retrait,Long>{

    @Query("select r from Retrait r where r.client.numCpte like :x or r.client.nomCli like :x")
    public List<Retrait> rechRetrait(@Param("x")String motCle);
    @Query("select r from Retrait r where  r.dateRetrait =:x ")
    public List<Retrait> rechRetraitByDate(@Param("x") Date date);
}
