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

	public static StateNode<String> getOrCreateNode(StateMachine<String> stateMachine, StateNode<String> parent, CLIFrontend frontend, Exam exam) {
		if (!nodeCache.containsKey(exam)) nodeCache.put(exam, createNode(stateMachine, parent, frontend, exam));
		return nodeCache.get(exam);
	}

	public static StateNode<String> createNode(StateMachine<String> stateMachine, StateNode<String> parent, CLIFrontend frontend, Exam exam) {
		StateNode<String> node = stateMachine.addNode(_ -> {
			frontend.out.printf("Module: %s, Version: %s%n", exam.getModule().getName(), exam.getVersion());
			List<Exam.QuestionReference> questions = exam.getQuestions();

			for (int i = 0; i < questions.size(); i++) {
				frontend.out.println();

				Exam.QuestionReference questionReference = questions.get(i);
				Question question = questionReference.getQuestion();

				frontend.out.printf("Question %d:%n", i + 1);
				frontend.out.printf("\t%s%n", question.getText());

				frontend.out.println("Answer:");
				frontend.out.printf("\t%s%n", question.getAnswer());

				frontend.out.print("Learning goals: ");
				if (!question.getGoals().isEmpty()) {
					for (LearningGoal goal : question.getGoals()) frontend.out.printf("%s, ", goal.getDescription());
					frontend.out.print("\b\b\n"); // Erase the final ", " and finish the line
				} else frontend.out.println("None");

				frontend.out.printf("Length: %dm, Points: %d, Type: %s, Level: %s, Shortable: %s%n", question.getLength().toMinutes(), question.getPoints(), question.getType().name(), question.getLevel().name(), questionReference.isShortable() ? "Yes" : "No");
			}

			frontend.out.println();

			frontend.out.println("1. Edit");
			frontend.out.println("2. Back");
		});

		node.addConnection(input -> input.equals("1") || input.equalsIgnoreCase("edit"), node, _ -> frontend.out.println("Editing exams is not implemented yet")); // TODO
		node.addConnection(input -> input.equals("2") || input.equalsIgnoreCase("back"), parent);
		node.setDefaultTarget(node, _ -> frontend.out.println("Invalid option, please try again"));

		return node;
	}
}