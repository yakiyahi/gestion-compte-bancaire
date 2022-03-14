package com.yakiyahi.ProjectApiRest.services;


import com.yakiyahi.ProjectApiRest.dao.ClientRepository;
import com.yakiyahi.ProjectApiRest.dao.VersementRepository;
import com.yakiyahi.ProjectApiRest.entities.Client;
import com.yakiyahi.ProjectApiRest.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class VersementService {
    @Autowired
    VersementRepository versementRepository;

    @Autowired
    ClientRepository clientRepository;
    //Recherche d une compte
    @RequestMapping(value = "/versparmotcle/{motCle}",method = RequestMethod.GET)
    public List<Versement> RechVers(@PathVariable String motCle){
       try {

           return versementRepository.rechVers(motCle);
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }

    }

    @RequestMapping(value = "/allversements",method = RequestMethod.GET)
    public List<Versement> allVersements(){
        return versementRepository.findAll();
    }
    //Rechercher un versement
    @RequestMapping(value = "/allversements/{mot_cle}",method = RequestMethod.GET)
    public List<Versement> rechVersements(String motCle){
        return versementRepository.rechVers("%"+motCle+"%");
    }
    //Ajout d une direction
    @RequestMapping(value = "/allversements",method = RequestMethod.POST)
    public Versement saveVersement(@RequestBody Versement versement){

        try {

            long montant = versement.getMontaVers();
            Client client = versement.getClient();
            System.out.println(client);
            long solde = client.getSolde();
            solde = solde + montant;
            client.setSolde(solde);

            clientRepository.save(client);
            versement.setClient(client);
            return versementRepository.save(versement);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    //Modification d'un versment
    @RequestMapping(value = "/versements",method = RequestMethod.PUT)
    public Versement updateVersement(@RequestBody Versement versement){
        Versement vers = versementRepository.findById(versement.getIdVers()).get();
        Client cli = versement.getClient();

        long montant_int = vers.getMontaVers();
        long mont_amodifier = versement.getMontaVers();
        long solde = cli.getSolde();

        long ecart_mont=0;

        //SI le montant ajouté avant est supperieur au montant de modification on fait la soustraction pour voir l'cart du montant
        if(montant_int > mont_amodifier){


            /*On ajout le nombre difference du nombre de montant
            vu que le montant a modifier est inferieur au monatant qu'on a ajouté avant alors
            sa veut dire que le monant initial on doit le diminuer vu qu'il est inferieur.
            et l'ecart_mont est la difference entre le montant initial et montant a modifier
            * */
            ecart_mont = montant_int - mont_amodifier;
            long solde_final = solde - ecart_mont;
            cli.setSolde(solde_final);
            clientRepository.save(cli);

        }else if(mont_amodifier > montant_int){
            ecart_mont = mont_amodifier - montant_int;
            // le montant final est l'ecart entre le montant initial et le monatant a modifier plus ajouté au montant initial
            long solde_final = solde + ecart_mont;
            cli.setSolde(solde_final);
            clientRepository.save(cli);
        }

        vers.setClient(cli);
        vers.setMontaVers(versement.getMontaVers());
        vers.setDateVers(versement.getDateVers());

        return versementRepository.save(vers);
    }
    //Suppression d'un versement
    @RequestMapping(value = "/allversements/{id}",method = RequestMethod.DELETE)
    public String  deleteVersement(@PathVariable Long id){
        try{
            Versement versement = versementRepository.findById(id).get();
            Client client = versement.getClient();

            //une fois le versement annuler alors on soustre le montant du versment effectué au solde du cient initial
            long solde_actuel = client.getSolde();
            long montant_supp =  versement.getMontaVers();

            long solde_final = solde_actuel - montant_supp;
            client.setSolde(solde_final);
            clientRepository.save(client);
            versementRepository .deleteById(id);
            return "SUCCESS";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "ERROR";
        }
    }
}

