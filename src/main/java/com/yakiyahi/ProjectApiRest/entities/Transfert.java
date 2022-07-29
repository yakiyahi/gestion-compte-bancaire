package com.yakiyahi.ProjectApiRest.entities;

import lombok.Data;

@Data
public class Transfert {
    Client cli_src;
    Client cli_dest;
    long solde_trans;

    public Transfert() {
        super();
    }

    public Transfert(Client cli_src, Client cli_dest, long solde_trans) {
        this.cli_src = cli_src;
        this.cli_dest = cli_dest;
        this.solde_trans = solde_trans;
    }

    public Client getCli_src() {
        return cli_src;
    }

    public void setCli_src(Client cli_src) {
        this.cli_src = cli_src;
    }

    public Client getCli_dest() {
        return cli_dest;
    }

    public void setCli_dest(Client cli_dest) {
        this.cli_dest = cli_dest;
    }

    public long getSolde_trans() {
        return solde_trans;
    }

    public void setSolde_trans(long solde_trans) {
        this.solde_trans = solde_trans;
    }

    @Override
    public String toString() {
        return "Transfert{" +
                "cli_src=" + cli_src +
                ", cli_dest=" + cli_dest +
                ", solde_trans=" + solde_trans +
                '}';
    }
}
