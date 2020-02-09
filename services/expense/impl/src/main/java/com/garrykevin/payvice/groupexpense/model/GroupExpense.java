package com.garrykevin.payvice.groupexpense.model;

import com.garrykevin.payvice.user.model.User;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class GroupExpense {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  @Column
  private String name;

  @ManyToMany
  @JoinTable(name="group_expense_member",joinColumns = @JoinColumn(name = "id"),
  inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<User> members;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupExpense")
  List<Expense> expenses;
}
