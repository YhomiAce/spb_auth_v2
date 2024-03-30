package com.ace.jwtAuth;

import com.ace.jwtAuth.entities.Role;
import com.ace.jwtAuth.entities.User;
import com.ace.jwtAuth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class JwtAuthApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthApplication.class, args);
	}

	public void run(String... args){
		Optional<User> admin = userRepository.findByRole(Role.ADMIN);
		if(admin.isEmpty()){
			User user = new User();
			user.setRole(Role.ADMIN);
			user.setEmail("super@admin.com");
			user.setLastName("Admin");
			user.setFirstName("Super");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}

}
