package com.lance.demo.springboot.statemachine;

import com.lance.demo.springboot.statemachine.enums.TurnstileEvents;
import com.lance.demo.springboot.statemachine.enums.TurnstileStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

/**
 * Created by perdonare on 2017/5/26.
 */
@Service
public class TurnstileService {
    @Autowired
    private StateMachine<TurnstileStates, TurnstileEvents> stateMachine;

    void onSignals() {
        stateMachine.start();
        stateMachine.sendEvent(TurnstileEvents.COIN);
        stateMachine.sendEvent(TurnstileEvents.PUSH);
        stateMachine.stop();
    }
}
