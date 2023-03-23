package com.example.restservice.dao;

import org.springframework.stereotype.Repository;
import com.example.restservice.models.Users;
import com.example.restservice.models.User;

@Repository
public class UserDAO {
  private static Users list = new Users();

  static
  {
    list.getUserList().add(
      new User(1, "Bob", "bob@example.com")
    );
    list.getUserList().add(
      new User(2, "Bill", "bill@example.com")
    );
    list.getUserList().add(
      new User(3, "Mickey", "bob@example.com")
    );
  }

  public Users getAllUsers() {
    return list;
  }

  public void addUser(User user) {
    list.getUserList().add(user);
  }

  public User getUserById(Integer id) {
    return list.getUserById(id);
  }
  
}
