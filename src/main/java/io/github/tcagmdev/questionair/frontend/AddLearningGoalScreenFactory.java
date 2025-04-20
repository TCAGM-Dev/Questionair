package io.github.tcagmdev.questionair.frontend;

import io.github.tcagmdev.questionair.data.LearningGoal;
import io.github.tcagmdev.questionair.util.ValueReference;
import io.github.tcagmdev.statesmith.StateMachine;
import io.github.tcagmdev.statesmith.StateNode;

public abstract class AddLearningGoalScreenFactory {
	public static StateNode<String> createNode(StateMachine<String> stateMachine, StateNode<String> parent, CLIFrontend frontend) {
		ValueReference<String> description = new ValueReference<>();
		ValueReference<String> location = new ValueReference<>();

		StateNode<String> descriptionNode = stateMachine.addNode(_ -> frontend.out.println("Description:"), description::set);
		StateNode<String> locationNode = stateMachine.addNode(_ -> frontend.out.println("Location:"), location::set);
		StateNode<String> saveNode = stateMachine.addNode(_ -> {
			LearningGoal goal = new LearningGoal(description.get(), location.get());
			frontend.getApp().DATA.addGoal(goal);

			frontend.out.println("Created new learning goal");

			stateMachine.setCurrentNode(LearningGoalInfoScreenFactory.getOrCreateNode(stateMachine, parent, frontend, goal));
		});

		descriptionNode.addConnection(String::isEmpty, descriptionNode, _ -> frontend.out.println("Description cannot be empty, please try again%n"));
		descriptionNode.setDefaultTarget(locationNode);

		locationNode.addConnection(String::isEmpty, locationNode, _ -> frontend.out.println("Location cannot be empty, please try again%n"));
		locationNode.setDefaultTarget(saveNode);

		return descriptionNode;
	}
}