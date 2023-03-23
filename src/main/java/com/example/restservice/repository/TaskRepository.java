package com.example.restservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restservice.models.Task;


public interface TaskRepository extends JpaRepository<Task, Long>{
  Task findById(long id);
  Task deleteById(long id);
}
