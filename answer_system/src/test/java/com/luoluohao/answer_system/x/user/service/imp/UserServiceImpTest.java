package com.luoluohao.answer_system.x.user.service.imp;

import com.luoluohao.answer_system.x.as.user.service.imp.UserServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImpTest {

    @Autowired
    private UserServiceImp userServiceImp;
    @Test
    public void getAllUser() {
//        List<User> allUser = userServiceImp.getAllUser();
//        System.out.println(allUser.size());
    }
}