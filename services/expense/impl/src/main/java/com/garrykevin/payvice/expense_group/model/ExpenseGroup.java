package com.garrykevin.payvice.expense_group.model;

import com.garrykevin.payvice.user.model.User;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Data
public class ExpenseGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;

  @Column
  private String name;

  @ManyToMany
  @JoinTable(name="expense_group_users",joinColumns = @JoinColumn(name = "id"),
  inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<User> members;

}
