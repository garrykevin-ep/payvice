package com.garrykevin.expense_group;

import static org.mockito.Mockito.when;

import com.garrykevin.payvice.groupexpense.CreateGroupExpenseParam;
import com.garrykevin.payvice.groupexpense.GroupExpenseMemberParam;
import com.garrykevin.payvice.groupexpense.impl.GroupExpenseDtoServiceImpl;
import com.garrykevin.payvice.groupexpense.model.GroupExpense;
import com.garrykevin.payvice.groupexpense.repository.GroupExpenseRepository;
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
@Import(GroupExpenseDtoServiceImpl.class)
public class GroupExpenseServiceTest {

  @MockBean
  UserMapper userMapper;

  @MockBean
  UserDtoService userDtoService;

  @MockBean
  GroupExpenseRepository groupExpenseRepository;

  @Test
  public void createExpenseGroup_withUserid_returnsExpenseGroupDto(){
    //setup input
    CreateGroupExpenseParam createGroupExpenseParam = new CreateGroupExpenseParam();
    createGroupExpenseParam.setName("hello");
    GroupExpenseMemberParam member1 = new GroupExpenseMemberParam();
    member1.setId(1l);
    GroupExpenseMemberParam member2 = new GroupExpenseMemberParam();
    member2.setId(2l);
    createGroupExpenseParam.setMembers(Arrays.asList(member1,member2));
    UserDto userDto1 = new UserDto();
    UserDto userDto2 = new UserDto();
    userDto1.setId(1l);
    userDto2.setId(2l);
    //mocking
    when(userDtoService.getByIds(Set.of(1l,2l))).thenReturn(Set.of(userDto1,userDto2));

    GroupExpense groupExpense = new GroupExpense();
    groupExpense.setName("hello");
    User user1 = new User();
    User user2 = new User();
    groupExpense.setMembers(Set.of(user1,user2));
    when(groupExpenseRepository.save(groupExpense)).thenReturn(groupExpense);

  }


  public void createExpenseGroup_withUserDoesNotExist_throwsUserNotFoundException(){
  }


  //TODO:
//  public void createExpenseGroup_withEmail(){
//  }

}
