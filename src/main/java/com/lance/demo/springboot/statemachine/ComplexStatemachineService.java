package com.lance.demo.springboot.statemachine;

import com.lance.demo.springboot.statemachine.enums.ComplexEvents;
import com.lance.demo.springboot.statemachine.enums.ComplexStates;
import com.lance.demo.springboot.statemachine.enums.Events;
import com.lance.demo.springboot.statemachine.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

/**
 * Created by perdonare on 2017/5/24.
 */
@Service
public class ComplexStatemachineService {
    @Autowired
    StateMachine<ComplexStates, ComplexEvents> stateMachine;

    void doSignals() {
        stateMachine.start();
        stateMachine.sendEvent(ComplexEvents.SEND);
        stateMachine.sendEvent(ComplexEvents.NOTICE);
        stateMachine.sendEvent(ComplexEvents.FAIL);
        stateMachine.sendEvent(ComplexEvents.SEND);
        stateMachine.stop();
    }
}
