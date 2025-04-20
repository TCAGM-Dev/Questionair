package io.github.tcagmdev.questionair.frontend;

import io.github.tcagmdev.questionair.data.Exam;
import io.github.tcagmdev.questionair.util.ValueReference;
import io.github.tcagmdev.statesmith.StateMachine;
import io.github.tcagmdev.statesmith.StateNode;

import java.util.HashMap;
import java.util.Map;

public abstract class ViewExamsScreenFactory {
	public static StateNode<String> createNode(StateMachine<String> stateMachine, StateNode<String> parent, CLIFrontend frontend) {
		Map<String, Integer> idMap = new HashMap<>();
		ValueReference<String> backIndex = new ValueReference<>();

		StateNode<String> node = stateMachine.addNode(_ -> {
			frontend.out.println("Exams:");

			idMap.clear();
			int currentIndex = 1;
			for (Map.Entry<Integer, Exam> entry : frontend.getApp().DATA.getExams().entrySet()) {
				idMap.put(String.valueOf(currentIndex), entry.getKey());
				frontend.out.printf("%d. %s%n", currentIndex, entry.getValue().getModule().getName());
				currentIndex++;
			}

			if (frontend.getApp().DATA.getExams().isEmpty()) frontend.out.println("None");

			frontend.out.println();

			frontend.out.printf("%d. Back%n", currentIndex);
			backIndex.set(String.valueOf(currentIndex));
		});

		node.addConnection(idMap::containsKey, input -> ExamInfoScreenFactory.getOrCreateNode(stateMachine, node, frontend, frontend.getApp().DATA.getExam(idMap.get(input))));
		node.addConnection(input -> idMap.isEmpty() || input.equals(backIndex.get()) || input.equalsIgnoreCase("back"), parent);
		node.setDefaultTarget(node, _ -> frontend.out.println("Invalid option. Please try again"));

		return node;
	}
}