package com.yakiyahi.ProjectApiRest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transfert {
    Client cli_src;
    Client cli_dest;
    long solde_trans;

}
