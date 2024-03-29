package com.yakiyahi.ProjectApiRest;
import com.yakiyahi.ProjectApiRest.entities.AppUser;
import com.yakiyahi.ProjectApiRest.entities.UsrRole;
import com.yakiyahi.ProjectApiRest.services.interfaces.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProjectApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApiRestApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner start(UserService userService){
		return args -> {
			//Ajouter les premieres utilisateurs et roles
			/*userService.saveRole(new UsrRole(null,"ROLE_ADMIN"));
			userService.saveRole(new UsrRole(null,"ROLE_SUPER_ADMIN"));
			userService.saveRole(new UsrRole(null,"ROLE_USER"));

			userService.saveUser(new AppUser("yaki","yakiyahi"));
			userService.saveUser(new AppUser("admin","admin123"));

			userService.addRoleToUser("yaki","ROLE_ADMIN");
			userService.addRoleToUser("admin","ROLE_SUPER_ADMIN");*/


		};
	}

}
