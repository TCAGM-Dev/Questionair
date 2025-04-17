package io.github.tcagmdev.questionair.data;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Question {
	private final Set<LearningGoal> goals = new HashSet<>();
	private int points;
	private Duration length;
	private QuestionType type;
	private String text;
	private String answer;
	private QuestionLevel level;

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

	public void setPoints(int points) {
		this.points = points;
	}
	public int getPoints() {
		return this.points;
	}

	public void setLength(Duration length) {
		this.length = length;
	}
	public Duration getLength() {
		return this.length;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}
	public QuestionType getType() {
		return this.type;
	}

	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return this.text;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswer() {
		return this.answer;
	}

	public void setLevel(QuestionLevel level) {
		this.level = level;
	}
	public QuestionLevel getLevel() {
		return this.level;
	}
}