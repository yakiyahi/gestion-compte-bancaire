package com.yakiyahi.ProjectApiRest;


import com.yakiyahi.ProjectApiRest.dao.ClientRepository;
import com.yakiyahi.ProjectApiRest.entities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase
public class ClientRepositoryTeste {
    @Autowired
    ClientRepository clientRepository;

    @Test
    public void saveClient(){
        Client client = new Client("CL012","wiwi",Long.parseLong("82500"));

                 Client cli = clientRepository.save(client);
                Assertions.assertEquals(cli,client);
    }
}
