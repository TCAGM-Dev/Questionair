package io.github.tcagmdev.questionair.frontend;

import io.github.tcagmdev.questionair.App;
import io.github.tcagmdev.questionair.data.LearningGoal;
import io.github.tcagmdev.questionair.util.ValueReference;
import io.github.tcagmdev.statesmith.StateMachine;
import io.github.tcagmdev.statesmith.StateNode;

import java.util.HashMap;
import java.util.Map;

public abstract class ViewLearningGoalsScreenFactory {
	public static StateNode<String> createNode(StateMachine<String> stateMachine, StateNode<String> parent, App app) {
		Map<String, Integer> idMap = new HashMap<>();
		ValueReference<String> backIndex = new ValueReference<>();

		StateNode<String> node = stateMachine.addNode(_ -> {
			System.out.println("Learning goals:");

			idMap.clear();
			int currentIndex = 1;
			for (Map.Entry<Integer, LearningGoal> entry : app.DATA.getGoals().entrySet()) {
				idMap.put(String.valueOf(currentIndex), entry.getKey());
				System.out.printf("%d. %s%n", currentIndex, entry.getValue().getDescription());
				currentIndex++;
			}

			if (app.DATA.getGoals().isEmpty()) System.out.println("None");

			System.out.println();

			System.out.printf("%d. Back%n", currentIndex);
			backIndex.set(String.valueOf(currentIndex));
		});

		node.addConnection(idMap::containsKey, input -> LearningGoalInfoScreenFactory.getOrCreateNode(stateMachine, node, app.DATA.getGoal(idMap.get(input))));
		node.addConnection(input -> idMap.isEmpty() || input.equals(backIndex.get()) || input.equalsIgnoreCase("back"), parent);
		node.setDefaultTarget(node, _ -> System.out.println("Invalid option. Please try again"));

		return node;
	}
}