package com.yakiyahi.ProjectApiRest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Retrait {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRetrait;
    @OneToOne(cascade = CascadeType.MERGE ,fetch = FetchType.EAGER)
    @JoinColumn(name = "numCpte")
    private Client client;
    private String numCheck;
    private long montantRettrait;
    @Temporal(TemporalType.DATE)
    private Date dateRetrait;
}
