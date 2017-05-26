package com.lance.demo.springboot.statemachine;

import com.lance.demo.springboot.App;
import com.lance.demo.springboot.statemachine.config.CommonConfiguration;
import com.lance.demo.springboot.statemachine.config.StateMachineCommands;
import com.lance.demo.springboot.statemachine.enums.TurnstileEvents;
import com.lance.demo.springboot.statemachine.enums.TurnstileStates;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.ObjectStateMachine;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineSystemConstants;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
/**
 * Created by perdonare on 2017/5/26.
 */
public class TurnstileServiceTest {
    private AnnotationConfigApplicationContext context;

    private StateMachine<TurnstileStates, TurnstileEvents> machine;

    private TestListener listener;

    private StateMachineCommands commands;

    @Test
    public void testNotStarted() throws Exception {
        assertThat(commands.state(), is("No state"));
    }

    @Test
    public void testInitialState() throws Exception {
        machine.start();
        listener.stateChangedLatch.await(1, TimeUnit.SECONDS);
        listener.stateEnteredLatch.await(1, TimeUnit.SECONDS);
        assertThat(machine.getState().getIds(), contains(TurnstileStates.LOCKED));
        assertThat(listener.statesEntered.size(), is(1));
        assertThat(listener.statesEntered.get(0).getId(), is(TurnstileStates.LOCKED));
        assertThat(listener.statesExited.size(), is(0));
    }

    @Test
    public void testTransition() throws Exception {
        machine.start();
        machine.sendEvent(TurnstileEvents.COIN);
        listener.stateChangedLatch.await(1, TimeUnit.SECONDS);
        listener.stateEnteredLatch.await(1, TimeUnit.SECONDS);
        listener.transitionLatch.await(1, TimeUnit.SECONDS);
        assertThat(listener.statesEntered.size(), is(2));
        assertThat(listener.transitions.size(), is(2));
        assertThat(listener.transitionCount, is(2));
    }














    static class Config {

        @Autowired
        private StateMachine<TurnstileStates,TurnstileEvents> machine;

        @Bean
        public StateMachineListener<TurnstileStates, TurnstileEvents> stateMachineListener() {
            TestListener listener = new TestListener();
            machine.addStateListener(listener);
            return listener;
        }
    }

    static class TestListener extends StateMachineListenerAdapter<TurnstileStates, TurnstileEvents> {

        //for init test
       /* volatile CountDownLatch stateChangedLatch = new CountDownLatch(1);
        volatile CountDownLatch stateEnteredLatch = new CountDownLatch(2);
        volatile CountDownLatch stateExitedLatch = new CountDownLatch(0);
        volatile CountDownLatch transitionLatch = new CountDownLatch(0); */
        //for transition test
        volatile CountDownLatch stateChangedLatch = new CountDownLatch(1);
        volatile CountDownLatch stateEnteredLatch = new CountDownLatch(2);
        volatile CountDownLatch stateExitedLatch = new CountDownLatch(0);
        volatile CountDownLatch transitionLatch = new CountDownLatch(4);
        volatile List<Transition<TurnstileStates, TurnstileEvents>> transitions = new ArrayList<>();
        List<State<TurnstileStates, TurnstileEvents>> statesEntered = new ArrayList<>();
        List<State<TurnstileStates, TurnstileEvents>> statesExited = new ArrayList<>();
        volatile int transitionCount = 0;

        @Override
        public void stateChanged(State<TurnstileStates, TurnstileEvents> from, State<TurnstileStates, TurnstileEvents> to) {
            stateChangedLatch.countDown();
        }

        @Override
        public void stateEntered(State<TurnstileStates, TurnstileEvents> state) {
            statesEntered.add(state);
            stateEnteredLatch.countDown();
        }

        @Override
        public void stateExited(State<TurnstileStates, TurnstileEvents> state) {
            statesExited.add(state);
            stateExitedLatch.countDown();
        }

        @Override
        public void transition(Transition<TurnstileStates, TurnstileEvents> transition) {
            transitions.add(transition);
            transitionLatch.countDown();
            transitionCount++;
        }

    }

    @SuppressWarnings("unchecked")
    @Before
    public void setup() {
        context = new AnnotationConfigApplicationContext();
        context.register(App.class,Config.class);
        context.refresh();
        machine = context.getBean(StateMachineSystemConstants.DEFAULT_ID_STATEMACHINE, ObjectStateMachine.class);
        listener = context.getBean(TestListener.class);
        commands = context.getBean(StateMachineCommands.class);
    }

    @After
    public void clean() {
        machine.stop();
        context.close();
        context = null;
        machine = null;
    }

}