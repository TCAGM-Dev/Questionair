package io.github.tcagmdev.questionair.frontend;

import io.github.tcagmdev.questionair.App;
import io.github.tcagmdev.statesmith.StateMachine;
import io.github.tcagmdev.statesmith.StateNode;

public abstract class HomeScreenFactory {
	public static StateNode<String> createNode(StateMachine<String> stateMachine, App app, StateNode<String> exitNode) {
		StateNode<String> node = stateMachine.addNode(_ -> System.out.println("""
		Welcome to Questionair!
		Please select one of the following options:
		1. Make exam
		2. View exams
		3. View learning goals
		4. Exit
		"""));
		StateNode<String> viewExamsScreen = ViewExamsScreenFactory.createNode(stateMachine, node, app);
		StateNode<String> viewGoalsScreen = null; // TODO

		node.addConnection(input -> input.equals("1"), node, _ -> System.out.println("Making exams is not implemented yet")); // TODO
		node.addConnection(input -> input.equals("2"), viewExamsScreen);
		node.addConnection(input -> input.equals("3"), viewGoalsScreen);
		node.addConnection(input -> input.equals("4"), exitNode);
		node.setDefaultTarget(node, _ -> System.out.println("Invalid option, please try again"));

		return node;
	}
}