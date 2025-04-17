package io.github.tcagmdev.questionair.data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LearningGoal {
	private String description;
	private String location;
	private final Set<LearningGoal> children = new HashSet<>();

	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return this.description;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocation() {
		return this.location;
	}

	public void addChild(LearningGoal child) {
		this.children.add(child);
	}
	public void removeChild(LearningGoal child) {
		this.children.remove(child);
	}
	public boolean hasChild(LearningGoal child) {
		return this.children.contains(child);
	}
	public Set<LearningGoal> getChildren() {
		return Collections.unmodifiableSet(this.children);
	}
}