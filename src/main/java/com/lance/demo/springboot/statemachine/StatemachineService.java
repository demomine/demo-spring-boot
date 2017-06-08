package com.lance.demo.springboot.statemachine;

import com.lance.demo.springboot.statemachine.enums.Events;
import com.lance.demo.springboot.statemachine.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

/**
 * Created by perdonare on 2017/5/24.
 */
//@Service
public class StatemachineService {
    @Autowired
    StateMachine<States, Events> stateMachine;

    void doSignals() {
        stateMachine.start();
        stateMachine.sendEvent(Events.EVENT1);
        stateMachine.sendEvent(Events.EVENT2);
    }
}
