package com.yakiyahi.ProjectApiRest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data

public class Client {
    @Id
    @Column(length=50)
    private String numCpte;
    private String nomCli;
    private Long solde;


    public Client() {
        super();
    }

    public Client(String numCpte, String nomCli, Long solde) {
        this.numCpte = numCpte;
        this.nomCli = nomCli;
        this.solde = solde;
    }

    public String getNumCpte() {
        return numCpte;
    }

    public void setNumCpte(String numCpte) {
        this.numCpte = numCpte;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }

    public Long getSolde() {
        return solde;
    }

    public void setSolde(Long solde) {
        this.solde = solde;
    }
}
