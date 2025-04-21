package io.github.tcagmdev.questionair.test;

import io.github.tcagmdev.questionair.data.*;
import io.github.tcagmdev.questionair.data.Module;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class ExamTest {
	@Test
	void test() {
		Module module = new Module("Language");

		LearningGoal g1 = new LearningGoal("letters", "here");
		LearningGoal g2 = new LearningGoal("words", "here");
		g2.addChild(g1);
		LearningGoal g3 = new LearningGoal("sentences", "here");
		g3.addChild(g2);
		LearningGoal g4 = new LearningGoal("punctuation", "here");
		g4.addChild(g3);
		LearningGoal g5 = new LearningGoal("capital letters", "here");
		g5.addChild(g1);
		g5.addChild(g3);

		Question q1 = new Question("\"help\" is word/letter/sentence", "word", 10, Duration.ofMinutes(1), QuestionType.MULTIPLE_CHOICE, QuestionLevel.APPLICATION2);
		q1.addGoal(g1);
		q1.addGoal(g2);
		q1.addGoal(g3);

		Question q2 = new Question("is the word \"amsterdam\" supposed to have a capital letter?", "yes", 4_000_000, Duration.ofSeconds(2), QuestionType.MULTIPLE_CHOICE, QuestionLevel.APPLICATION2);
		q2.addGoal(g2);
		q2.addGoal(g5);

		Exam exam = new Exam("A", module);
		exam.addQuestion(q1, true);
		exam.addQuestion(q2, false);

		Assertions.assertInstanceOf(Exam.class, exam);
		Assertions.assertEquals(q1, exam.getQuestions().getFirst().getQuestion());
		Assertions.assertEquals(q2, exam.getQuestions().get(1).getQuestion());
		Assertions.assertEquals("A", exam.getVersion());
		Assertions.assertEquals(module, exam.getModule());
	}
}