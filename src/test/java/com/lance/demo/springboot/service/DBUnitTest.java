package com.lance.demo.springboot.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.lance.demo.springboot.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
//@DatabaseSetup("/META-INF/dbtest/sampleData.xml")
public class DBUnitTest {
    @Autowired
    private UserService userService;

    @DatabaseSetup("")
    @Before
    public void setup() {

    }

    @DatabaseTearDown("")
    @After
    public void teardown() {

    }

    @Test
    public void getUser() throws Exception {
        User user = userService.getUser("1");
        assertEquals(user.getAge(), 27);
    }

    @Test
    @DatabaseSetup("UserSetup.xml")
    @ExpectedDatabase("UserExpect.xml")
    public void updateUser() throws Exception {
        User user = new User("1");
        user.setName("lance");
        userService.updateUser(user);
    }

}