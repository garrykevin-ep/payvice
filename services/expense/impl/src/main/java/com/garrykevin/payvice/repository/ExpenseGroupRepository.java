package com.garrykevin.payvice.repository;

import com.garrykevin.payvice.model.ExpenseGroup;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseGroupRepository extends CrudRepository<ExpenseGroup,Long> {

  ExpenseGroup save(ExpenseGroup expenseGroup);


}
