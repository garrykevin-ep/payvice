package com.garrykevin.payvice.controllers;

import com.garrykevin.payvice.UserDto;
import com.garrykevin.payvice.model.ExpenseGroup;
import com.garrykevin.payvice.repository.ExpenseGroupRepository;
import com.garrykevin.payvice.UserDtoService;
import com.garrykevin.payvice.model.User;
import com.garrykevin.payvice.repository.UserRepository;
import com.garrykevin.payvice.request.ExpenseGroupRequest;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense-group/")
public class ExpenseGroupController {

  @Autowired
  ExpenseGroupRepository expenseGroupRepository;

  @Autowired
  UserDtoService userDtoService;

  @Autowired
  UserRepository userRepository;

  @PostMapping
  public String createExpenseGroup(@RequestBody ExpenseGroupRequest expenseGroupRequest){
    ExpenseGroup expenseGroup = new ExpenseGroup();
//    Set<UserDto> userDtos = userDtoService.getByIds(expenseGroupRequest.getUserIds().stream().collect(
//      Collectors.toList()));
    Set<User> users = userRepository.findByIdIn(expenseGroupRequest.getUserIds());
    expenseGroup.setName("new Expense");
    expenseGroup.setUsers(users);
    expenseGroupRepository.save(expenseGroup);
   return "ok";
  }
}
