package com.garrykevin.payvice.groupexpense;


import java.util.List;

public interface GroupExpenseDtoService {

  GroupExpenseDto create(CreateGroupExpenseParam createGroupExpenseParam);

  List<GroupExpenseDto> getAll(Long userId);

  GroupExpenseDto getById(Long groupExpenseId);

}
