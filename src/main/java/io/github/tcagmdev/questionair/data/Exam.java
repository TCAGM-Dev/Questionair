package io.github.tcagmdev.questionair.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exam {
	public static class QuestionReference {
		private final Question question;
		private boolean isShortable;

		public QuestionReference(Question question, boolean isShortable) {
			this.question = question;
			this.isShortable = isShortable;
		}

		public Question getQuestion() {
			return this.question;
		}
		public boolean isShortable() {
			return this.isShortable;
		}
	}

	private final List<QuestionReference> questions = new ArrayList<>();
	private final String version;
	private final Module module;

	public Exam(String version, Module module) {
		this.version = version;
		this.module = module;
	}

	public String getVersion() {
		return this.version;
	}
	public Module getModule() {
		return this.module;
	}

	public void addQuestion(Question question, boolean isShortable) {
		this.questions.add(new QuestionReference(question, isShortable));
	}
	public List<QuestionReference> getQuestions() {
		return Collections.unmodifiableList(this.questions);
	}
	public void removeQuestion(Question question) {
		for (int i = 0; i < this.questions.size(); i++) if (this.questions.get(i).getQuestion().equals(question)) {
			this.questions.remove(i);
			break;
		}
	}
	public void clearQuestions() {
		this.questions.clear();
	}
}