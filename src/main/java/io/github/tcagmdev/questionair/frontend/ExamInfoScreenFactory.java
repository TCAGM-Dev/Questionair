package io.github.tcagmdev.questionair.frontend;

import io.github.tcagmdev.questionair.data.Exam;
import io.github.tcagmdev.statesmith.StateMachine;
import io.github.tcagmdev.statesmith.StateNode;

import java.util.HashMap;
import java.util.Map;

public abstract class ExamInfoScreenFactory {
	private static final Map<Exam, StateNode<String>> nodeCache = new HashMap<>();

	public static StateNode<String> getOrCreateNode(StateMachine<String> stateMachine, StateNode<String> parent, Exam exam) {
		if (!nodeCache.containsKey(exam)) nodeCache.put(exam, createNode(stateMachine, parent, exam));
		return nodeCache.get(exam);
	}

	public static StateNode<String> createNode(StateMachine<String> stateMachine, StateNode<String> parent, Exam exam) {
		StateNode<String> node = stateMachine.addNode();

		return node;
	}
}