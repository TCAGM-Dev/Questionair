package io.github.tcagmdev.questionair.frontend;

import io.github.tcagmdev.questionair.data.DataManager;
import io.github.tcagmdev.questionair.data.Exam;
import io.github.tcagmdev.questionair.util.ValueReference;
import io.github.tcagmdev.statesmith.StateMachine;
import io.github.tcagmdev.statesmith.StateNode;

import java.util.HashMap;
import java.util.Map;

public class ViewExamsScreenFactory {
	public static StateNode<String> createNode(StateMachine<String> stateMachine, StateNode<String> parent, DataManager dataManager) {
		Map<String, Integer> idMap = new HashMap<>();
		ValueReference<String> backIndex = new ValueReference<>();

		StateNode<String> node = stateMachine.addNode(_ -> {
			System.out.println("Exams:");

			idMap.clear();
			int currentIndex = 1;
			for (Map.Entry<Integer, Exam> entry : dataManager.getExams().entrySet()) {
				idMap.put(String.valueOf(currentIndex), entry.getKey());
				System.out.printf("%d. %s%n", currentIndex, entry.getValue().getModule().getName());
				currentIndex++;
			}

			if (dataManager.getExams().isEmpty()) System.out.println("None");

			System.out.printf("%d. Back%n", currentIndex);
			backIndex.set(String.valueOf(currentIndex));
		});

		node.addConnection(idMap::containsKey, input -> ExamInfoScreenFactory.getOrCreateNode(stateMachine, node, dataManager.getExam(idMap.get(input))));
		node.addConnection(input -> idMap.isEmpty() || input.equals(backIndex.get()), parent);
		node.setDefaultTarget(node, _ -> System.out.println("Invalid option. Please try again"));

		return node;
	}
}