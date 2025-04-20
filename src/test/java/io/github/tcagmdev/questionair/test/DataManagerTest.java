package io.github.tcagmdev.questionair.test;

import io.github.tcagmdev.questionair.data.*;
import io.github.tcagmdev.questionair.data.Module;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class DataManagerTest {
	@Test
	void test() {
		LearningGoal g1 = new LearningGoal("hello", "hi");
		LearningGoal g2 = new LearningGoal("ok", "bye");
		g2.addChild(g1);

		Module m1 = new io.github.tcagmdev.questionair.data.Module("one");
		m1.addGoal(g1);
		Module m2 = new Module("both");
		m2.addGoal(g1);
		m2.addGoal(g2);

		Question q1 = new Question("waht does a cow say", "moo", 69, Duration.ofNanos(420), QuestionType.FILL_IN, QuestionLevel.INSIGHT);
		Question q2 = new Question("what do you say", "boohoo", 420, Duration.ofNanos(69), QuestionType.MULTIPLE_CHOICE, QuestionLevel.REPRODUCTION);

		Exam e1 = new Exam("A", m1);
		Exam e2 = new Exam("B", m2);


		DataManager dataManager = new DataManager();

		dataManager.addGoal(g1);
		dataManager.addGoal(g2);
		dataManager.addModule(m1);
		dataManager.addModule(m2);
		dataManager.addQuestion(q1);
		dataManager.addQuestion(q2);
		dataManager.addExam(e1);
		dataManager.addExam(e2);


		Assertions.assertEquals(2, dataManager.getGoals().size());
		Assertions.assertEquals(2, dataManager.getModules().size());
		Assertions.assertEquals(2, dataManager.getQuestions().size());
		Assertions.assertEquals(2, dataManager.getExams().size());

		Assertions.assertEquals(g1, dataManager.getGoal(1));
		Assertions.assertEquals(g2, dataManager.getGoal(2));
		Assertions.assertEquals(m1, dataManager.getModule(1));
		Assertions.assertEquals(m2, dataManager.getModule(2));
		Assertions.assertEquals(q1, dataManager.getQuestion(1));
		Assertions.assertEquals(q2, dataManager.getQuestion(2));
		Assertions.assertEquals(e1, dataManager.getExam(1));
		Assertions.assertEquals(e2, dataManager.getExam(2));
	}
}