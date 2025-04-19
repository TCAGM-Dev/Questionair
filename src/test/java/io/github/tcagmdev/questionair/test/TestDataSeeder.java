package io.github.tcagmdev.questionair.test;

import io.github.tcagmdev.questionair.data.*;
import io.github.tcagmdev.questionair.data.Module;

import java.time.Duration;

public class TestDataSeeder implements DataSeeder {
	public void seed(EntryConsumer<Exam> putExam, EntryConsumer<LearningGoal> putGoal, EntryConsumer<Module> putModule, EntryConsumer<Question> putQuestion) {
		LearningGoal g0 = new LearningGoal("getallen", "h0.1");
		LearningGoal g1 = new LearningGoal("optellen", "h1.1");
		g1.addChild(g0);
		LearningGoal g2 = new LearningGoal("aftrekken", "h1.2");
		g2.addChild(g1);
		LearningGoal g3 = new LearningGoal("vermenigvuldigen", "h1.3");
		g3.addChild(g1);
		LearningGoal g4 = new LearningGoal("delen", "h1.4");
		g4.addChild(g3);
		LearningGoal g5 = new LearningGoal("rekenvolgorde", "h1.5");
		g5.addChild(g1);
		g5.addChild(g2);
		g5.addChild(g3);
		g5.addChild(g4);
		LearningGoal g6 = new LearningGoal("machten", "h2.1");
		g6.addChild(g3);
		LearningGoal g7 = new LearningGoal("wortels", "h2.2");
		g7.addChild(g6);
		LearningGoal g8 = new LearningGoal("hogeremachtswortels", "h2.2");
		g8.addChild(g7);
		LearningGoal g9 = new LearningGoal("exponentiële functies", "h2.3");
		g9.addChild(g6);
		LearningGoal g10 = new LearningGoal("logaritmes", "h2.4");
		g10.addChild(g9);
		LearningGoal g11 = new LearningGoal("vergelijkingen", "h3.1");
		g11.addChild(g0);
		g11.addChild(g5);
		LearningGoal g12 = new LearningGoal("herleiden", "h3.2");
		g12.addChild(g11);

		Module m1 = new Module("Wiskunde V1 P1");
		m1.addGoal(g5);
		m1.addGoal(g6);
		Module m2 = new Module("Wiskunde V1 P2");
		m2.addGoal(g8);
		m2.addGoal(g9);
		m2.addGoal(g10);
		m2.addGoal(g12);

		Question q1 = new Question("Wat is 1+1?", "2", 1, Duration.ofMinutes(1), QuestionType.OPEN, QuestionLevel.APPLICATION1);
		q1.addGoal(g1);
		Question q2 = new Question("Wat is 6/2(1+2)?", "6", 4, Duration.ofMinutes(2), QuestionType.OPEN, QuestionLevel.APPLICATION2);
		q2.addGoal(g1);
		q2.addGoal(g2);
		q2.addGoal(g3);
		q2.addGoal(g4);
		q2.addGoal(g5);
		Question q3 = new Question("3 * 4 / ? = 2", "6", 4, Duration.ofMinutes(4), QuestionType.OPEN, QuestionLevel.APPLICATION2);
		q3.addGoal(g3);
		q3.addGoal(g4);
		q3.addGoal(g12);

		Exam e1 = new Exam("A", m1);
		e1.addQuestion(q1, true);
		e1.addQuestion(q2, false);
		Exam e2 = new Exam("A", m2);
		e2.addQuestion(q2, false);
		e2.addQuestion(q3, false);
		Exam e3 = new Exam("B", m2);
		e3.addQuestion(q3, false);
		e3.addQuestion(q2, false);


		putGoal.accept(0, g0);
		putGoal.accept(1, g1);
		putGoal.accept(2, g2);
		putGoal.accept(3, g3);
		putGoal.accept(4, g4);
		putGoal.accept(5, g5);
		putGoal.accept(6, g6);
		putGoal.accept(7, g7);
		putGoal.accept(8, g8);
		putGoal.accept(9, g9);
		putGoal.accept(10, g10);
		putGoal.accept(11, g11);
		putGoal.accept(12, g12);

		putModule.accept(1, m1);
		putModule.accept(2, m2);

		putQuestion.accept(1, q1);
		putQuestion.accept(2, q2);
		putQuestion.accept(3, q3);

		putExam.accept(1, e1);
		putExam.accept(2, e2);
		putExam.accept(3, e3);
	}
}