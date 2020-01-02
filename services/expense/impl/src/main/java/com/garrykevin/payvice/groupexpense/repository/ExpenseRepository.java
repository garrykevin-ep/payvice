package com.garrykevin.payvice.groupexpense.repository;

import com.garrykevin.payvice.groupexpense.model.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense,Long> {

  Expense save(Expense expense);

}
