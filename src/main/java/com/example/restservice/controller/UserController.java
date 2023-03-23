package com.example.restservice.controller;

import java.net.URI;
import java.util.Dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.HttpStatus;

import com.example.restservice.dao.UserDAO;
import com.example.restservice.models.User;
import com.example.restservice.models.Users;
import com.example.restservice.utils.ErrorResponse;

@RestController
@RequestMapping(path="/users")
public class UserController {
  @Autowired
  private UserDAO userDao;

  @GetMapping(path="", produces="application/json")
  public Users getUsers() {
    return userDao.getAllUsers();
  }

  @GetMapping(path="/{id}", produces="application/json")
  public ResponseEntity<Object> getUser(@PathVariable Integer id) {
    if (id == null) {
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse("Must include User id in URL"));
    }

    User user = userDao.getUserById(id);

    if (user == null) {
      return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(new ErrorResponse(String.format("User not found with id: %d", id)));
    }

    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @PostMapping(path="", consumes="application/json", produces="application/json")
  public ResponseEntity<Object> addUser(@RequestBody User user) {
    Integer id = userDao.getAllUsers().getUserList().size() + 1;

    user.setId(id);

    userDao.addUser(user);

    URI location = ServletUriComponentsBuilder
                      .fromCurrentRequest()
                      .path("/{id}")
                      .buildAndExpand(user.getId())
                      .toUri();

    return ResponseEntity.created(location).body(user);
  }
}
