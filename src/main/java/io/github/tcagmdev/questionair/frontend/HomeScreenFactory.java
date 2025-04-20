package io.github.tcagmdev.questionair.frontend;

import io.github.tcagmdev.questionair.App;
import io.github.tcagmdev.statesmith.StateMachine;
import io.github.tcagmdev.statesmith.StateNode;

public class HomeScreenFactory {
	public static StateNode<String> createNode(StateMachine<String> stateMachine, App app, StateNode<String> exitNode) {
		StateNode<String> node = stateMachine.addNode(_ -> System.out.println("""
			Welcome to Questionair!
			Please select one of the following options:
			1. Make exam
			2. View exams
			3. Exit
		"""));
		StateNode<String> viewExamsScreen = ViewExamsScreenFactory.createNode(stateMachine, node, app);

		node.addConnection(input -> input.equalsIgnoreCase("2"), viewExamsScreen);
		node.addConnection(input -> input.equals("3"), exitNode);

		return node;
	}
}