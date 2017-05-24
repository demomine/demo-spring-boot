package com.lance.demo.springboot.statemachine.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * Created by perdonare on 2017/5/24.
 * state machine model
 */
@WithStateMachine
@Slf4j
public class Mybean {
    @OnTransition(target = "STATE1")
    public void toState1() {
        log.info("to state1");
    }

    @OnTransition(target = "STATE2")
    public void toState2() {
        log.info("to state2");
    }
}
