package com.luoluohao.answer_system.baseTest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {
   @Autowired
   WebApplicationContext context;

   public MockMvc mvc;

   MockHttpSession session;

   @Before
    public void setupMockMvc(){
       mvc = MockMvcBuilders.webAppContextSetup(context).build();
   }
}
