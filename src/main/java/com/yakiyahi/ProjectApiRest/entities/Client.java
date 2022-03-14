package com.yakiyahi.ProjectApiRest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Client {
    @Id
    @Column(length=50)
    private String numCpte;
    private String nomCli;
    private Long solde;


}
