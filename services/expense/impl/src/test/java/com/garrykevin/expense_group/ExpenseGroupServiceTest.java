package com.garrykevin.expense_group;

import static org.mockito.Mockito.when;

import com.garrykevin.payvice.expense_group.CreateExpenseGroupParam;
import com.garrykevin.payvice.expense_group.ExpenseGroupMemberParam;
import com.garrykevin.payvice.expense_group.impl.ExpenseGroupDtoServiceImpl;
import com.garrykevin.payvice.expense_group.model.ExpenseGroup;
import com.garrykevin.payvice.expense_group.repository.ExpenseGroupRepository;
import com.garrykevin.payvice.user.UserDto;
import com.garrykevin.payvice.user.UserDtoService;
import com.garrykevin.payvice.user.mapper.UserMapper;
import com.garrykevin.payvice.user.model.User;
import java.util.Arrays;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Import(ExpenseGroupDtoServiceImpl.class)
public class ExpenseGroupServiceTest {

  @MockBean
  UserMapper userMapper;

  @MockBean
  UserDtoService userDtoService;

  @MockBean
  ExpenseGroupRepository expenseGroupRepository;

  @Test
  public void createExpenseGroup_withUserid_returnsExpenseGroupDto(){
    //setup input
    CreateExpenseGroupParam createExpenseGroupParam = new CreateExpenseGroupParam();
    createExpenseGroupParam.setName("hello");
    ExpenseGroupMemberParam member1 = new ExpenseGroupMemberParam();
    member1.setId(1l);
    ExpenseGroupMemberParam member2 = new ExpenseGroupMemberParam();
    member2.setId(2l);
    createExpenseGroupParam.setMembers(Arrays.asList(member1,member2));
    UserDto userDto1 = new UserDto();
    UserDto userDto2 = new UserDto();
    userDto1.setId(1l);
    userDto2.setId(2l);
    //mocking
    when(userDtoService.getByIds(Set.of(1l,2l))).thenReturn(Set.of(userDto1,userDto2));

    ExpenseGroup expenseGroup = new ExpenseGroup();
    expenseGroup.setName("hello");
    User user1 = new User();
    User user2 = new User();
    expenseGroup.setMembers(Set.of(user1,user2));
    when(expenseGroupRepository.save(expenseGroup)).thenReturn(expenseGroup);

  }


  public void createExpenseGroup_withUserDoesNotExist_throwsUserNotFoundException(){
  }


  //TODO:
//  public void createExpenseGroup_withEmail(){
//  }

}
