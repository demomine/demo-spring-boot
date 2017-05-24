package com.lance.demo.springboot.statemachine;

import com.lance.demo.springboot.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by perdonare on 2017/5/24.
 */
@SpringBootTest(classes = {App.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class StatemachineServiceTest {
    @Autowired
    private StatemachineService statemachineService;
    @Test
    public void doSignals() throws Exception {
        statemachineService.doSignals();
    }

}