package com.garrykevin.payvice.expense_group.repository;

import com.garrykevin.payvice.expense_group.model.ExpenseGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseGroupRepository extends CrudRepository<ExpenseGroup,Long> {

  ExpenseGroup save(ExpenseGroup expenseGroup);


}
