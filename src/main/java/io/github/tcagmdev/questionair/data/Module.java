package io.github.tcagmdev.questionair.data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Module {
	private String name;
	private final Set<LearningGoal> goals = new HashSet<>();

	public Module(String name) {
		this.name = name;
	}

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
	public boolean hasDescendantGoal(LearningGoal goal) {
		if (this.hasGoal(goal)) return true;
		for (LearningGoal g : this.goals) {
			if (g.hasDescendant(goal)) return true;
		}
		return false;
	}
	public Set<LearningGoal> getGoals() {
		return Collections.unmodifiableSet(this.goals);
	}
	public Set<LearningGoal> getDescendantGoals() {
		Set<LearningGoal> result = new HashSet<>(this.goals);

		for (LearningGoal goal : this.goals) {
			result.addAll(goal.getDescendants());
		}

		return Collections.unmodifiableSet(result);
	}
}