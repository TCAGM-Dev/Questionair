package io.github.tcagmdev.questionair.data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Module {
	private String name;
	private final Set<LearningGoal> goals = new HashSet<>();

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void addGoal(LearningGoal goal) {
		this.goals.add(goal);
	}
	public void removeGoal(LearningGoal goal) {
		this.goals.remove(goal);
	}
	public boolean hasGoal(LearningGoal goal) {
		return this.goals.contains(goal);
	}
	public Set<LearningGoal> getGoals() {
		return Collections.unmodifiableSet(this.goals);
	}
}