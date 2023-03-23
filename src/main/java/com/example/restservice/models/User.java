package com.example.restservice.models;

public class User {
  private Integer id;
  private String name;
  private String email;

  public User(Integer id, String name, String email) {
    super();

    this.id = id;
    this.name = name;
    this.email = email;
  }

  @Override
  public String toString() {
    return String.format("User[id=%s, name=%s, email=%s]", id, name, email);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}

