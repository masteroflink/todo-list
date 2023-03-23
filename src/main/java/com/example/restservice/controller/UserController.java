package com.example.restservice.controller;

import java.net.URI;
import java.util.Dictionary;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.HttpStatus;

import com.example.restservice.models.User;
import com.example.restservice.models.Task;
import com.example.restservice.repository.UserRepository;

@RestController
@RequestMapping(path="/user")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping(produces="application/json")
  public ResponseEntity<Object> getUsers() {
    try {
      List<User> users = userRepository.findAll();

      return ResponseEntity.status(HttpStatus.OK).body(users);
    } catch (Exception e) {
      return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(e.toString());
    }
  }

  @GetMapping(path="/{id}", produces="application/json")
  public ResponseEntity<Object> getUser(@PathVariable long id) {
    User user = userRepository.findById(id);

    if (user == null) {
      return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(String.format("User not found with id: %d", id));
    }

    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @PostMapping(path="/{id}", consumes="application/json", produces="application/json")
  public ResponseEntity<Object> editUser(@PathVariable long id, @RequestBody User user) {
    User u = userRepository.findById(id);
    if (u == null) {
      return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(String.format("User not found with id: %d", id));
    }

    u.setFields(user);
    userRepository.save(u);


    return ResponseEntity.status(HttpStatus.OK).body(u);
  }

  @PostMapping(consumes="application/json", produces="application/json")
  public ResponseEntity<Object> addUser(@RequestBody User user) {
    userRepository.save(user);

    URI location = ServletUriComponentsBuilder
                      .fromCurrentRequest()
                      .path("/{id}")
                      .buildAndExpand(user.getId())
                      .toUri();

    return ResponseEntity.created(location).body(user);
  }

  @PostMapping(path="/{user_id}/tasks/add", consumes="application/json", produces="application/json")
  public ResponseEntity<Object> addUserTask(@PathVariable long user_id, @RequestBody Task task) {
    User u = userRepository.findById(user_id);
    u.addTask(task);
    userRepository.save(u);

    URI location = ServletUriComponentsBuilder
                      .fromCurrentRequest()
                      .path("/{id}")
                      .buildAndExpand(user_id)
                      .toUri();

    return ResponseEntity.created(location).body(u);
  }

  @DeleteMapping(path="/{id}", produces="application/json")
  public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
    try {
      userRepository.deleteById(id);
      return ResponseEntity
            .status(HttpStatus.OK)
            .body(String.format("User with id %d successfully deleted", id));
    } catch (Exception e) {
      return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(e.toString());
    }
  }
}
