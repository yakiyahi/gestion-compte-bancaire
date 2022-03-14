package com.yakiyahi.ProjectApiRest;

import com.yakiyahi.ProjectApiRest.dao.ClientRepository;
import com.yakiyahi.ProjectApiRest.dao.RetraitRepository;
import com.yakiyahi.ProjectApiRest.dao.VersementRepository;
import com.yakiyahi.ProjectApiRest.entities.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ProjectApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApiRestApplication.class, args);
	}
	@Bean
	CommandLineRunner start(ClientRepository clientRepository, RetraitRepository retraitRepository, VersementRepository versementRepository, RepositoryRestConfiguration repositoryRestConfiguration){
		return args -> {


 			System.out.println(clientRepository.findAll());

		};
	}

}
