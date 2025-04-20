package io.github.tcagmdev.questionair.frontend;

import io.github.tcagmdev.questionair.data.Exam;
import io.github.tcagmdev.questionair.data.LearningGoal;
import io.github.tcagmdev.questionair.data.Question;
import io.github.tcagmdev.statesmith.StateMachine;
import io.github.tcagmdev.statesmith.StateNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ExamInfoScreenFactory {
	private static final Map<Exam, StateNode<String>> nodeCache = new HashMap<>();

	public static StateNode<String> getOrCreateNode(StateMachine<String> stateMachine, StateNode<String> parent, Exam exam) {
		if (!nodeCache.containsKey(exam)) nodeCache.put(exam, createNode(stateMachine, parent, exam));
		return nodeCache.get(exam);
	}

	public static StateNode<String> createNode(StateMachine<String> stateMachine, StateNode<String> parent, Exam exam) {
		StateNode<String> node = stateMachine.addNode(_ -> {
			System.out.printf("Module: %s, Version: %s%n", exam.getModule().getName(), exam.getVersion());
			List<Exam.QuestionReference> questions = exam.getQuestions();

			for (int i = 0; i < questions.size(); i++) {
				System.out.println();

				Exam.QuestionReference questionReference = questions.get(i);
				Question question = questionReference.getQuestion();

				System.out.printf("Question %d:%n", i + 1);
				System.out.printf("\t%s%n", question.getText());

				System.out.println("Answer:");
				System.out.printf("\t%s%n", question.getAnswer());

				System.out.print("Learning goals: ");
				if (!question.getGoals().isEmpty()) {
					for (LearningGoal goal : question.getGoals()) System.out.printf("%s, ", goal.getDescription());
					System.out.print("\b\b\n"); // Erase the final ", " and finish the line
				} else System.out.println("None");

				System.out.printf("Length: %dm, Points: %d, Type: %s, Level: %s, Shortable: %s%n", question.getLength().toMinutes(), question.getPoints(), question.getType().name(), question.getLevel().name(), questionReference.isShortable() ? "Yes" : "No");
			}

			System.out.println();

			System.out.println("1. Edit");
			System.out.println("2. Back");
		});

		node.addConnection(input -> input.equals("1") || input.equalsIgnoreCase("edit"), node, _ -> System.out.println("Editing exams is not implemented yet")); // TODO
		node.addConnection(input -> input.equals("2") || input.equalsIgnoreCase("back"), parent);
		node.setDefaultTarget(node, _ -> System.out.println("Invalid option, please try again"));

		return node;
	}
}