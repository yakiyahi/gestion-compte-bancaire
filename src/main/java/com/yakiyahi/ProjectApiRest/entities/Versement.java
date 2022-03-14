package com.yakiyahi.ProjectApiRest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Versement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVers;
    @OneToOne(cascade = CascadeType.MERGE ,fetch = FetchType.EAGER)
    @JoinColumn(name = "numCpte")
    private Client client;
    private long montaVers;
    @Temporal(TemporalType.DATE)
    private Date dateVers;

}
