package com.yakiyahi.ProjectApiRest.services;

import com.yakiyahi.ProjectApiRest.dao.ClientRepository;
import com.yakiyahi.ProjectApiRest.dao.RetraitRepository;
import com.yakiyahi.ProjectApiRest.entities.Client;
import com.yakiyahi.ProjectApiRest.entities.Retrait;
import com.yakiyahi.ProjectApiRest.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
public class RetraitService {
    @Autowired
    RetraitRepository retraitRepository;

    @Autowired
    ClientRepository clientRepository;
    //Recherche d une retrait
    @RequestMapping(value = "/retraitparmotcle/{motCle}",method = RequestMethod.GET)
    public List<Retrait> RechRetrait(@PathVariable String motCle){
        try {

            return retraitRepository.rechRetrait(motCle);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "/allretraits",method = RequestMethod.GET)
    public List<Retrait> allRetrait(){

        return retraitRepository.findAll();
    }

    //Ajout d une retrait
    @RequestMapping(value = "/allretraits",method = RequestMethod.POST)
    public Retrait saveRetrait(@RequestBody Retrait retrait){

        try {
            long montant = retrait.getMontantRettrait();
            Client client = retrait.getClient();
            long solde = client.getSolde();

            if(solde>montant){
                solde = solde - montant;
                client.setSolde(solde);
                clientRepository.save(client);
                retrait.setClient(client);

                return retraitRepository.save(retrait);
            }else{
                return null;
            }

        }catch (Exception e){
            System.out.println("Une erreur est survenu: "+e.getMessage());
            return null;
        }
    }
    @RequestMapping(value = "/retraits",method = RequestMethod.PUT)
    public Retrait updateRetrait(@RequestBody Retrait retrait){
        Retrait retr = retraitRepository.findById(retrait.getIdRetrait()).get();

        Client cli = retrait.getClient();

        long montant_int = retr.getMontantRettrait();
        long mont_amodifier = retrait.getMontantRettrait();
        long solde = cli.getSolde();

        long ecart_mont = 0;

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
            long solde_final = solde - ecart_mont;
            cli.setSolde(solde_final);
            clientRepository.save(cli);
        }

        retr.setClient(cli);
        retr.setMontantRettrait(retrait.getMontantRettrait());
        retr.setDateRetrait(retrait.getDateRetrait());
        retr.setNumCheck(retrait.getNumCheck());
        return retraitRepository.save(retr);
    }
    //Suppression d'un client
    @RequestMapping(value = "/allretraits/{id}",method = RequestMethod.DELETE)
    public String  deleteRetraits(@PathVariable Long id){
        try{
            Retrait retrait = retraitRepository.findById(id).get();
            Client client = retrait.getClient();

            //une fois le retrait annuler alors on ajoute le montant du retrait effectué au solde du cient initial
            long solde_actuel = client.getSolde();
            long montant_supp =  retrait.getMontantRettrait();

            long solde_final = solde_actuel + montant_supp;
            client.setSolde(solde_final);
            clientRepository.save(client);

            retraitRepository.deleteById(id);
            return "SUCCESS";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "ERROR";
        }
    }
}
