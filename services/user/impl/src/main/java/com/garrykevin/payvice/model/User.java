package com.garrykevin.payvice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
  @Id
  private long id;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "password")
  private String password;
  @Column(name = "email")
  private String email;
  @Column(name = "phone")
  private String phone;
}
