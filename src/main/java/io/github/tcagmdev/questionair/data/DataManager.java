package io.github.tcagmdev.questionair.data;

import java.util.HashMap;
import java.util.Map;

public class DataManager {
	private final Map<Integer, Exam> exams = new HashMap<>();
	private final Map<Integer, LearningGoal> goals = new HashMap<>();
	private final Map<Integer, Module> modules = new HashMap<>();
	private final Map<Integer, Question> questions = new HashMap<>();

	private int examSequenceKey;
	private int goalSequenceKey;
	private int moduleSequenceKey;
	private int questionSequenceKey;

	public DataManager() {
		this.examSequenceKey = 0;
		this.goalSequenceKey = 0;
		this.moduleSequenceKey = 0;
		this.questionSequenceKey = 0;
	}
	public DataManager(DataSeeder seeder) {
		seeder.seed(this::putExam, this::putGoal, this::putModule, this::putQuestion);
	}

	public Exam getExam(int id) {
		return this.exams.get(id);
	}
	public void deleteExam(int id) {
		this.exams.remove(id);
	}
	public void addExam(Exam exam) {
		this.exams.put(++this.examSequenceKey, exam);
	}
	private void putExam(int id, Exam exam) {
		if (this.exams.containsKey(id)) throw new IllegalStateException("Attempt to put exam on an id that already exists");
		this.exams.put(id, exam);
	}

	public LearningGoal getGoal(int id) {
		return this.goals.get(id);
	}
	public void deleteGoal(int id) {
		this.goals.remove(id);
	}
	public void addGoal(LearningGoal goal) {
		this.goals.put(++this.goalSequenceKey, goal);
	}
	private void putGoal(int id, LearningGoal goal) {
		if (this.goals.containsKey(id)) throw new IllegalStateException("Attempt to put goal on an id that already exists");
		this.goals.put(id, goal);
	}

	public Module getModule(int id) {
		return this.modules.get(id);
	}
	public void deleteModule(int id) {
		this.modules.remove(id);
	}
	public void addModule(Module module) {
		this.modules.put(++this.moduleSequenceKey, module);
	}
	private void putModule(int id, Module module) {
		if (this.modules.containsKey(id)) throw new IllegalStateException("Attempt to put module on an id that already exists");
		this.modules.put(id, module);
	}

	public Question getQuestion(int id) {
		return this.questions.get(id);
	}
	public void deleteQuestion(int id) {
		this.questions.remove(id);
	}
	public void addQuestion(Question question) {
		this.questions.put(++this.questionSequenceKey, question);
	}
	private void putQuestion(int id, Question question) {
		if (this.questions.containsKey(id)) throw new IllegalStateException("Attempt to put question on an id that already exists");
		this.questions.put(id, question);
	}
}