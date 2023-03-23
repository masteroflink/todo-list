package com.example.restservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  @Column(nullable=false)
  private String name;
  @Column(nullable=false)
  private String email;
  @OneToMany(mappedBy="user", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
  @JsonManagedReference
  private List<Task> tasks = new ArrayList<>();
  
  @Column(updatable=false)
  @CreationTimestamp
  private Timestamp createdAt;
  @UpdateTimestamp
  private Timestamp updatedAt;

  protected User() {}

  public User(String name, String email) {
    this.name = name;
    this.email = email;
  }

  @Override
  public String toString() {
    return String.format("User[id=%s, name=%s, email=%s, createdAt=%s, updatedAt=%s]", id, name, email, createdAt.toString(), updatedAt.toString());
  }

  public void addTask(Task task) {
    tasks.add(task);
    task.setUser(this);
  }

  public void removeComment(Task task) {
    tasks.remove(task);
    task.setUser(null);
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public void setFields(User user) {
    this.name = user.getName();
    this.email = user.getEmail();
  }
}
