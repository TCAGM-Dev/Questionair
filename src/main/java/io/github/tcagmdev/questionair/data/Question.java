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

	public Question(String questionText, String answer, int points, Duration length, QuestionType type, QuestionLevel level) {
		this.text = questionText;
		this.answer = answer;
		this.points = points;
		this.length = length;
		this.type = type;
		this.level = level;
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