package io.github.tcagmdev.questionair.frontend;

import io.github.tcagmdev.questionair.App;
import io.github.tcagmdev.statesmith.StateMachine;
import io.github.tcagmdev.statesmith.StateNode;

public abstract class HomeScreenFactory {
	public static StateNode<String> createNode(StateMachine<String> stateMachine, App app, StateNode<String> exitNode) {
		StateNode<String> node = stateMachine.addNode(_ -> {
			System.out.println("Welcome to Questionair!");
			System.out.println("Please select one of the following options:");
			System.out.println("1. Make exam");
			System.out.println("2. View exams");
			System.out.println("3. View learning goals");
			System.out.println("4. Exit");
		});
		StateNode<String> viewExamsScreen = ViewExamsScreenFactory.createNode(stateMachine, node, app);
		StateNode<String> viewGoalsScreen = ViewLearningGoalsScreenFactory.createNode(stateMachine, node, app);

		node.addConnection(input -> input.equals("1"), node, _ -> System.out.println("Making exams is not implemented yet")); // TODO
		node.addConnection(input -> input.equals("2"), viewExamsScreen);
		node.addConnection(input -> input.equals("3"), viewGoalsScreen);
		node.addConnection(input -> input.equals("4"), exitNode);
		node.setDefaultTarget(node, _ -> System.out.println("Invalid option, please try again"));

		return node;
	}
}