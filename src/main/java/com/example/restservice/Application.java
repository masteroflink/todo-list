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
import com.example.restservice.repository.TaskRepository;
import com.example.restservice.models.User;
import com.example.restservice.models.Task;


@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
  public CommandLineRunner initializeUserData(UserRepository repository) {
    return (args) -> {
			log.info("Insert example users...");
			User[] users = new User[]{
				new User("Mickey", "mickey@example.com"),
				new User("Donald", "donald@example.com"),
				new User("Zelda", "zelda@example.com"),
				new User("Link", "link@example.com")
			};
			
			
			log.info("Adding tasks to User 1...");
			log.info("-----------------------------------------------");
			Task[] tasks = new Task[]{
				new Task("Take out trash", "2023-04-11T00:00:00+00:00"),
				new Task("Read 2 hours", "2023-05-11T00:00:00+00:00"),
				new Task("Complete homework 1-5", "2023-04-02T00:00:00+00:00"),
				new Task("Do dishes", "2023-06-18T00:00:00+00:00")
			};

			for (Task task : tasks) {
				users[0].addTask(task);
			}

			for (User user : users) {
				repository.save(user);
			}


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
