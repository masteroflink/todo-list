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

import com.example.restservice.models.Task;
import com.example.restservice.models.User;
import com.example.restservice.repository.TaskRepository;
import com.example.restservice.repository.UserRepository;

@RestController
@RequestMapping(path="/task")
public class TaskController {
  @Autowired
  private TaskRepository taskRepository;

  @GetMapping(produces="application/json")
  public ResponseEntity<Object> getTasks() {
    try {
      List<Task> tasks = taskRepository.findAll();

      return ResponseEntity.status(HttpStatus.OK).body(tasks);
    } catch (Exception e) {
      return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(e.toString());
    }
  }

  @GetMapping(path="/{id}", produces="application/json")
  public ResponseEntity<Object> getTask(@PathVariable long id) {
    Task task = taskRepository.findById(id);

    if (task == null) {
      return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(String.format("Task not found with id: %d", id));
    }

    return ResponseEntity.status(HttpStatus.OK).body(task);
  }

  @DeleteMapping(path="/{id}", produces="application/json")
  public ResponseEntity<Object> deleteTask(@PathVariable("id") long id) {
    try {
      taskRepository.deleteById(id);
      return ResponseEntity
            .status(HttpStatus.OK)
            .body(String.format("Task with id %d successfully deleted", id));
    } catch (Exception e) {
      return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(e.toString());
    }
  }
}
