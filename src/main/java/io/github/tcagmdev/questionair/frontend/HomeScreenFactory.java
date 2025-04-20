package io.github.tcagmdev.questionair.frontend;

import io.github.tcagmdev.statesmith.StateMachine;
import io.github.tcagmdev.statesmith.StateNode;

public abstract class HomeScreenFactory {
	public static StateNode<String> createNode(StateMachine<String> stateMachine, CLIFrontend frontend, StateNode<String> exitNode) {
		StateNode<String> node = stateMachine.addNode(_ -> {
			frontend.out.println("Welcome to Questionair!");
			frontend.out.println("Please select one of the following options:");
			frontend.out.println("1. Make exam");
			frontend.out.println("2. View exams");
			frontend.out.println("3. View learning goals");
			frontend.out.println("4. Add learning goal");
			frontend.out.println("5. Exit");
		});
		StateNode<String> viewExamsScreen = ViewExamsScreenFactory.createNode(stateMachine, node, frontend);
		StateNode<String> viewGoalsScreen = ViewLearningGoalsScreenFactory.createNode(stateMachine, node, frontend);
		StateNode<String> addGoalScreen = AddLearningGoalScreenFactory.createNode(stateMachine, node, frontend);

		node.addConnection(input -> input.equals("1"), node, _ -> frontend.out.println("Making exams is not implemented yet")); // TODO
		node.addConnection(input -> input.equals("2"), viewExamsScreen);
		node.addConnection(input -> input.equals("3"), viewGoalsScreen);
		node.addConnection(input -> input.equals("4"), addGoalScreen);
		node.addConnection(input -> input.equals("5"), exitNode);
		node.setDefaultTarget(node, _ -> frontend.out.println("Invalid option, please try again"));

		return node;
	}
}