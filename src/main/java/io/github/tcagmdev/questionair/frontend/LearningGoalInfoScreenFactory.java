package io.github.tcagmdev.questionair.frontend;

import io.github.tcagmdev.questionair.data.LearningGoal;
import io.github.tcagmdev.statesmith.StateMachine;
import io.github.tcagmdev.statesmith.StateNode;

import java.util.HashMap;
import java.util.Map;

public abstract class LearningGoalInfoScreenFactory {
	private static final Map<LearningGoal, StateNode<String>> nodeCache = new HashMap<>();

	public static StateNode<String> getOrCreateNode(StateMachine<String> stateMachine, StateNode<String> parent, CLIFrontend frontend, LearningGoal goal) {
		if (!nodeCache.containsKey(goal)) nodeCache.put(goal, createNode(stateMachine, parent, frontend, goal));
		return nodeCache.get(goal);
	}

	public static StateNode<String> createNode(StateMachine<String> stateMachine, StateNode<String> parent, CLIFrontend frontend, LearningGoal goal) {
		StateNode<String> node = stateMachine.addNode(_ -> {
			frontend.out.printf("Goal: %s%n", goal.getDescription());
			frontend.out.printf("Can be found at: %s%n", goal.getLocation());

			frontend.out.print("Children: ");
			if (!goal.getChildren().isEmpty()) {
				for (LearningGoal child : goal.getChildren()) frontend.out.printf("%s, ", child.getDescription());
				frontend.out.print("\b\b\n"); // Erase the final ", " and finish the line
			} else frontend.out.println("None");

			frontend.out.print("Descendants: ");
			if (!goal.getChildren().isEmpty()) { // getChildren because the only way for getDescendants to be empty is for getChildren to also be, and its faster
				for (LearningGoal descendant : goal.getDescendants()) frontend.out.printf("%s, ", descendant.getDescription());
				frontend.out.print("\b\b\n"); // Erase the final ", " and finish the line
			} else frontend.out.println("None");

			frontend.out.println();

			frontend.out.println("1. Edit");
			frontend.out.println("2. Back");
		});

		node.addConnection(input -> input.equals("1") || input.equalsIgnoreCase("edit"), node, _ -> frontend.out.println("Editing learning goals is not implemented yet")); // TODO
		node.addConnection(input -> input.equals("2") || input.equalsIgnoreCase("back"), parent);
		node.setDefaultTarget(node, _ -> frontend.out.println("Invalid option, please try again"));

		return node;
	}
}