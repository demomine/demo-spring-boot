package com.lance.demo.springboot.statemachine.config;

import com.lance.demo.springboot.statemachine.enums.TurnstileEvents;
import com.lance.demo.springboot.statemachine.enums.TurnstileStates;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

@Component
public class StateMachineCommands extends AbstractStateMachineCommands<TurnstileStates, TurnstileEvents> {

	@CliCommand(value = "sm event", help = "Sends an event to a state machine")
	public String event(@CliOption(key = { "", "event" }, mandatory = true, help = "The event") final TurnstileEvents event) {
		getStateMachine().sendEvent(event);
		return "Event " + event + " send";
	}

}