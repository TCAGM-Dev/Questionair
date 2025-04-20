package io.github.tcagmdev.questionair.test;

import io.github.tcagmdev.questionair.App;
import io.github.tcagmdev.questionair.data.DataManager;
import io.github.tcagmdev.questionair.frontend.CLIFrontend;

public class ManualTest {
	public static void main(String[] args) throws Exception {
        DataManager dataManager = new DataManager(new TestDataSeeder());

        App app = new App(dataManager, "questionair-test");

        new CLIFrontend(app, System.in, System.out).start();
	}
}