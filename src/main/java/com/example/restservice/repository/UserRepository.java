package com.example.restservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restservice.models.User;


public interface UserRepository extends JpaRepository<User, Long>{
  User findById(long id);
  User deleteById(long id);
}
