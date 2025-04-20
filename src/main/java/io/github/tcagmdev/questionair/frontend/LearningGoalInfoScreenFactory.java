package io.github.tcagmdev.questionair.frontend;

import io.github.tcagmdev.questionair.data.LearningGoal;
import io.github.tcagmdev.statesmith.StateMachine;
import io.github.tcagmdev.statesmith.StateNode;

import java.util.HashMap;
import java.util.Map;

public abstract class LearningGoalInfoScreenFactory {
	private static final Map<LearningGoal, StateNode<String>> nodeCache = new HashMap<>();

	public static StateNode<String> getOrCreateNode(StateMachine<String> stateMachine, StateNode<String> parent, LearningGoal goal) {
		if (!nodeCache.containsKey(goal)) nodeCache.put(goal, createNode(stateMachine, parent, goal));
		return nodeCache.get(goal);
	}

	public static StateNode<String> createNode(StateMachine<String> stateMachine, StateNode<String> parent, LearningGoal goal) {
		StateNode<String> node = stateMachine.addNode(_ -> {
			System.out.printf("Goal: %s%n", goal.getDescription());
			System.out.printf("Can be found at: %s%n", goal.getLocation());

			System.out.print("Children: ");
			if (!goal.getChildren().isEmpty()) {
				for (LearningGoal child : goal.getChildren()) System.out.printf("%s, ", child.getDescription());
				System.out.print("\b\b\n"); // Erase the final ", " and finish the line
			} else System.out.println("None");

			System.out.print("Descendants: ");
			if (!goal.getChildren().isEmpty()) { // getChildren because the only way for getDescendants to be empty is for getChildren to also be, and its faster
				for (LearningGoal descendant : goal.getDescendants()) System.out.printf("%s, ", descendant.getDescription());
				System.out.print("\b\b\n"); // Erase the final ", " and finish the line
			} else System.out.println("None");

			System.out.println();

			System.out.println("1. Edit");
			System.out.println("2. Back");
		});

		node.addConnection(input -> input.equals("1") || input.equalsIgnoreCase("edit"), node, _ -> System.out.println("Editing learning goals is not implemented yet")); // TODO
		node.addConnection(input -> input.equals("2") || input.equalsIgnoreCase("back"), parent);
		node.setDefaultTarget(node, _ -> System.out.println("Invalid option, please try again"));

		return node;
	}
}