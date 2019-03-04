package com.luoluohao.answer_system.x.user.controller;

import com.luoluohao.answer_system.baseTest.ControllerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest extends ControllerTest {


    @Test
    public void login() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user/login")
                .param("account","xxxxx@qq.com")
                .param("pwd","xxx")
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print())
        ;
    }
}