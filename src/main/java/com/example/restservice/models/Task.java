package com.example.restservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import java.time.*;
import java.time.OffsetDateTime;
import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.example.restservice.models.User;

@Entity
@Embeddable
@Table(name = "tasks")
public class Task {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  @Column(nullable=false)
  private String title;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="user_id", nullable=false)
  @JsonBackReference
  private User user;
  private OffsetDateTime dueDate;
  
  @Column(updatable=false)
  @CreationTimestamp
  private Timestamp createdAt;
  @UpdateTimestamp
  private Timestamp updatedAt;

  protected Task() {}

  public Task(String title, String dueDate) {
    this.title = title;
    this.dueDate = OffsetDateTime.parse(dueDate);
  }

  @Override
  public String toString() {
    return String.format("Task[id=%s, title=%s, dueDate=%s]", id, title, dueDate.toString());
  }

  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task )) return false;
        return id != null && id.equals(((Task) o).getId());
    }
 
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public User getUser() {
    return user;
  }

  public OffsetDateTime getDueDate() {
    return dueDate;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUser(User user) {
    this.user = user;
  }
  
  public void setFields(Task task) {
    this.dueDate = task.getDueDate();
    this.title = task.getTitle();
  }
}
