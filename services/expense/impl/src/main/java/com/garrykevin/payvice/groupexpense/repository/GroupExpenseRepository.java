package com.garrykevin.payvice.groupexpense.repository;

import com.garrykevin.payvice.groupexpense.model.GroupExpense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupExpenseRepository extends CrudRepository<GroupExpense,Long> {

  GroupExpense save(GroupExpense groupExpense);

}
