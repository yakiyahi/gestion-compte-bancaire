package com.yakiyahi.ProjectApiRest.services;

import com.yakiyahi.ProjectApiRest.dao.ClientRepository;
import com.yakiyahi.ProjectApiRest.dao.RetraitRepository;
import com.yakiyahi.ProjectApiRest.dao.VersementRepository;
import com.yakiyahi.ProjectApiRest.entities.Client;
import com.yakiyahi.ProjectApiRest.entities.Retrait;
import com.yakiyahi.ProjectApiRest.entities.Transfert;
import com.yakiyahi.ProjectApiRest.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    VersementRepository versementRepository;
    RetraitRepository retraitRepository;

    //Recherche d une direction
    @RequestMapping(value = "/clientparmotcle/{motCle}",method = RequestMethod.GET)
    public List<Client> RechClient(@PathVariable String motCle){
        return clientRepository.rechClient("%"+motCle+"%");

    }

    @RequestMapping(value = "/clientbyid/{numCpte}",method = RequestMethod.GET)
    public Client RechClientById(@PathVariable String numCpte){
        Client client = clientRepository.findById(numCpte).get();
        System.out.println(client);
        return client;

    }

    @RequestMapping(value = "/graphs",method = RequestMethod.GET)
    public List<String> getGraphsInfo(){
        return clientRepository.grah();
    }
    @RequestMapping(value = "/allNumsComptes",method = RequestMethod.GET)
    public List<String> getAllNumsClients(){
        return clientRepository.numsClients();
    }

    @RequestMapping(value = "/allclients",method = RequestMethod.GET)
    public List<Client> allClient(){
        return clientRepository.allClientsOrderByNum();
    }

    //Modification du client
    @RequestMapping(value = "/clients",method = RequestMethod.PUT)
    public Client updateClient(@RequestBody Client client){
        Client cli = clientRepository.findById(client.getNumCpte()).get();
        cli.setNomCli(client.getNomCli());
        cli.setSolde(client.getSolde());
        return clientRepository.save(cli);
    }
    @RequestMapping(value = "/allclients",method = RequestMethod.POST)
    public Client addClient(@RequestBody Client client){
        try {
            Long sole =client.getSolde() ;
            return clientRepository.save(client);
        }catch (Exception e){
            return null;

        }


    }
    //Suppression d'un client
    @RequestMapping(value = "/allclients/{num_cpte}",method = RequestMethod.DELETE)
    public String  deleteClient(@PathVariable String num_cpte){
        try{
            clientRepository.deleteById(num_cpte);
            return "SUCCESS";

        }catch (Exception e){

            return "ERROR";
        }
    }
    @RequestMapping(value = "/allclientstrans",method = RequestMethod.POST)
    public Transfert transClient(@RequestBody Transfert transfert){

      try {
          Client src = transfert.getCli_src();
          Client dest = transfert.getCli_dest();

          long solde_cli_src = src.getSolde();
          long solde_cli_dest = dest.getSolde();

          long solde_transfert = transfert.getSolde_trans();

          dest.setSolde(solde_transfert + solde_cli_dest);
          src.setSolde(solde_cli_src - solde_transfert);

          //Enregistrement du transfert
          clientRepository.save(src);
          clientRepository.save(dest);
          return transfert;
      }catch (Exception e){
          return null;
      }
    }
}
