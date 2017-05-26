package com.lance.demo.springboot.statemachine.config;

import com.lance.demo.springboot.statemachine.enums.TurnstileEvents;
import com.lance.demo.springboot.statemachine.enums.TurnstileStates;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * Created by perdonare on 2017/5/26.
 */
@Configuration
@EnableStateMachine
public class TurnstileDemoConfig extends EnumStateMachineConfigurerAdapter<TurnstileStates,TurnstileEvents> {
    @Override
    public void configure(StateMachineStateConfigurer<TurnstileStates,TurnstileEvents>  states) throws Exception {
        states
                .withStates()
                .initial(TurnstileStates.LOCKED)
                .states(EnumSet.allOf(TurnstileStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<TurnstileStates,TurnstileEvents>  transitions) throws Exception {
        transitions
            .withExternal()
            .source(TurnstileStates.LOCKED)
            .target(TurnstileStates.UNLOCKED)
            .event(TurnstileEvents.COIN)
            .and()
            .withExternal()
            .source(TurnstileStates.UNLOCKED)
            .target(TurnstileStates.LOCKED)
            .event(TurnstileEvents.PUSH);
    }
}
