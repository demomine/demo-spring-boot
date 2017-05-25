package com.lance.demo.springboot.statemachine.config;

import com.lance.demo.springboot.statemachine.enums.ComplexEvents;
import com.lance.demo.springboot.statemachine.enums.ComplexStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by perdonare on 2017/5/25.
 */
@Configuration
@EnableStateMachine
@EnableAsync
@Slf4j
public class StatemachineConfigComplex extends EnumStateMachineConfigurerAdapter<ComplexStates,ComplexEvents> {
    @Override
    public void configure(StateMachineStateConfigurer<ComplexStates, ComplexEvents> states) throws Exception {
        states
                .withStates()
                .initial(ComplexStates.INIT, stateContext -> log.info("========init action"))
                .state(ComplexStates.DOING, stateContext -> log.info("========doing action in"), stateContext -> log.info("===========doing action out"))
                .state(ComplexStates.ACCEPT, getAcceptActions())
                .state(ComplexStates.SUCCESS, stateContext -> log.info("========success action"))
                .state(ComplexStates.FAIL, stateContext -> log.info("========fail action"));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ComplexStates, ComplexEvents> transitions) throws Exception {
        transitions.withExternal()
                    .source(ComplexStates.INIT).target(ComplexStates.DOING).event(ComplexEvents.SEND)
                .and().withExternal()
                    .source(ComplexStates.DOING).target(ComplexStates.ACCEPT).event(ComplexEvents.NOTICE)
                .and().withExternal()
                    .source(ComplexStates.ACCEPT).target(ComplexStates.FAIL).event(ComplexEvents.FAIL)
                .and().withExternal()
                    .source(ComplexStates.ACCEPT).target(ComplexStates.SUCCESS).event(ComplexEvents.SUCCESS)
                .and().withExternal()
                    .source(ComplexStates.FAIL).target(ComplexStates.DOING).event(ComplexEvents.SEND);
    }

    private List<Action<ComplexStates, ComplexEvents>> getAcceptActions() {
        List<Action<ComplexStates, ComplexEvents>> list = new ArrayList<>();
        list.add(stateContext -> log.info("========accept action 1"));
        list.add(stateContext -> log.info("=========accept action 2"));
        list.add(stateContext -> log.info("============accept action 3"));
        return list;
    }
}
