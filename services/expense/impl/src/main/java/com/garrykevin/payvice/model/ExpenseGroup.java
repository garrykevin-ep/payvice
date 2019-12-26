package com.garrykevin.payvice.model;

import com.garrykevin.payvice.model.User;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Data
public class ExpenseGroup {

  @Id
  @GeneratedValue
  private Long Id;

  @Column
  private String name;

  @ManyToMany
  @Cascade({CascadeType.ALL})
  private Set<User> users;

}
