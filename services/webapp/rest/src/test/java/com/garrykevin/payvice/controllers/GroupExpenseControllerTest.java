package com.garrykevin.payvice.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class GroupExpenseControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void createExpenseGroupTest() throws Exception {
    this.mockMvc.perform(post("/expense-group/")).andDo(print());
    return;
  }

}
