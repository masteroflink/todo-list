package com.example.restservice;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import com.example.restservice.repository.UserRepository;
import com.example.restservice.models.User;

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
  public CommandLineRunner demo(UserRepository repository) {
    return (args) -> {
      repository.save(new User("Mickey", "mickey@example.com"));
      repository.save(new User("Donald", "donald@example.com"));
      repository.save(new User("Zelda", "zelda@example.com"));
      repository.save(new User("Link", "link@example.com"));

      log.info("All Users:");
      log.info("-----------------------------------------------");
      for (User user : repository.findAll()) {
				log.info(user.toString());
      }
      log.info("");

      User user = repository.findById(1L);
      log.info("User 1 found:");
      log.info("-----------------------------------------------");
      log.info(user.toString());
      log.info("");
    };
  }
}
