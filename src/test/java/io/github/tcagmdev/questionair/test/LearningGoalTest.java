package io.github.tcagmdev.questionair.test;

import io.github.tcagmdev.questionair.data.LearningGoal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LearningGoalTest {
	@Test
	void test() {
		LearningGoal g1 = new LearningGoal("test", "here");
		LearningGoal g2 = new LearningGoal("goal", "and here");
		g2.addChild(g1);
		LearningGoal g3 = new LearningGoal("mooooo", "beeee");
		g3.addChild(g2);

		Assertions.assertEquals("test", g1.getDescription());
		Assertions.assertEquals("here", g1.getLocation());
		Assertions.assertEquals("goal", g2.getDescription());
		Assertions.assertEquals("and here", g2.getLocation());
		Assertions.assertEquals("mooooo", g3.getDescription());
		Assertions.assertEquals("beeee", g3.getLocation());

		Assertions.assertEquals(0, g1.getChildren().size());
		Assertions.assertEquals(1, g2.getChildren().size());
		Assertions.assertEquals(1, g3.getChildren().size());

		Assertions.assertEquals(0, g1.getDescendants().size());
		Assertions.assertEquals(1, g2.getDescendants().size());
		Assertions.assertEquals(2, g3.getDescendants().size());

		Assertions.assertTrue(g2.getChildren().contains(g1));
		Assertions.assertTrue(g3.getChildren().contains(g2));

		Assertions.assertTrue(g2.getDescendants().contains(g1));
		Assertions.assertTrue(g3.getDescendants().contains(g1));
		Assertions.assertTrue(g3.getDescendants().contains(g2));
	}
}