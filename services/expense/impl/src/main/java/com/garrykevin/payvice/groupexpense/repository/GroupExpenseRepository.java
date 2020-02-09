package com.garrykevin.payvice.groupexpense.repository;

import com.garrykevin.payvice.groupexpense.model.GroupExpense;
import com.garrykevin.payvice.user.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupExpenseRepository extends CrudRepository<GroupExpense,Long> {

  GroupExpense save(GroupExpense groupExpense);

  // TODO change to find by members
  List<GroupExpense> findByMembers(User user);

  Optional<GroupExpense> findById(Long id);

}
