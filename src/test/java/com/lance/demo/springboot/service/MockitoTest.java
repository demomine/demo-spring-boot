package com.lance.demo.springboot.service;

import com.lance.demo.springboot.User;
import com.lance.demo.springboot.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockitoTest {
    @InjectMocks
    private UserService userServiceSpy;


    @Mock
    private UserMapper userMapper;


    @Before
    public void before() {
        when(userServiceSpy.getUser("1")).thenReturn(new User("1"));
    }

    @Test
    public void updateSpy() {
        User user = userServiceSpy.getUser("1");
        assertThat(user.getId()).isEqualTo("1");
        verify(userMapper, times(1)).getUser("1");
    }
}

