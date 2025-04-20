package io.github.tcagmdev.questionair;

import io.github.tcagmdev.questionair.data.DataManager;
import io.github.tcagmdev.questionair.frontend.CLIFrontend;

public class Main {
	public static void main(String[] args) throws Exception {
		DataManager dataManager = new DataManager();

		App app = new App(dataManager, "questionair");

		new CLIFrontend(app, System.in, System.out).start();
	}
}