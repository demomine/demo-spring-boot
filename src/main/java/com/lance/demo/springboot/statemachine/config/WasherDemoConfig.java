package com.lance.demo.springboot.statemachine.config;

import com.lance.demo.springboot.statemachine.enums.WasherMachineEvents;
import com.lance.demo.springboot.statemachine.enums.WasherMachineStates;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.config.configurers.StateConfigurer;

/**
 * Created by perdonare on 2017/5/26.
 */
@Configuration
@EnableStateMachine
public class WasherDemoConfig extends EnumStateMachineConfigurerAdapter<WasherMachineStates, WasherMachineEvents> {
    @Override
    public void configure(StateMachineStateConfigurer<WasherMachineStates, WasherMachineEvents> states) throws Exception {
        states.withStates()
                .initial(WasherMachineStates.RUNNING)
                .state(WasherMachineStates.RINSING)
                .state(WasherMachineStates.DRYING)
                .state(WasherMachineStates.POWEROFF);
                // .history(WasherMachineStates.HISTORY,new StateConfigurer.History())
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<WasherMachineStates, WasherMachineEvents> transitions) throws Exception {
        super.configure(transitions);
    }
}
