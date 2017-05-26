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
import java.util.EnumSet;
import java.util.List;

/**
 * Created by perdonare on 2017/5/25.
 */
/*@Configuration
@EnableStateMachine
@EnableAsync*/
@Slf4j
public class StatemachineConfigComplex extends EnumStateMachineConfigurerAdapter<ComplexStates,ComplexEvents> {
    @Override
    public void configure(StateMachineStateConfigurer<ComplexStates, ComplexEvents> states) throws Exception {
        states
                .withStates()
                .initial(ComplexStates.INIT, stateContext -> log.info("========init action"))
                //异常只能在transition里面传递而不能再state中传递
                /*.state(ComplexStates.DOING, stateContext -> {log.info("========doing exception");stateContext.getException().setStackTrace(new RuntimeException("MyError").getStackTrace());},
                        stateContext -> log.info("===========dong exit exception"+stateContext.getException().getMessage()))*/

                .state(ComplexStates.DOING, stateContext -> log.info("========doing start"),
                        stateContext -> log.info("===========dong end"))
                .state(ComplexStates.ACCEPT, stateContext -> log.info("========accept action in"))
                .states(EnumSet.allOf(ComplexStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ComplexStates, ComplexEvents> transitions) throws Exception {
        transitions.withExternal()
                    .source(ComplexStates.INIT).target(ComplexStates.DOING).event(ComplexEvents.SEND)
                        .action(stateContext -> {log.info("++++++++++++exception throw");/*throw new RuntimeException("error");*/}
                            ,stateContext -> {log.info("++++++++++++exception get");})
                    //.guard(stateContext -> {log.info("============check doing exception guard");return stateContext.getException()==null;})
                .and().withExternal()
                    .source(ComplexStates.DOING).target(ComplexStates.ACCEPT).event(ComplexEvents.NOTICE)
                    .action(stateContext -> {log.info("++++++++++++accept");});
                    //.guard(stateContext -> {log.info("============check accept exception guard");return stateContext.getException()==null;});

    }


}
