package io.github.tcagmdev.questionair;

import io.github.tcagmdev.questionair.data.DataManager;
import io.github.tcagmdev.questionair.frontend.CLIFrontend;
import io.github.tcagmdev.questionair.logging.LoggingLevel;

public class Main {
	public static void main(String[] args) throws Exception {
		DataManager dataManager = new DataManager();

		App app = new App(dataManager, "questionair");

		app.LOGGER.setLevelEnabled(LoggingLevel.DEBUG, false);
		app.LOGGER.setLevelEnabled(LoggingLevel.INFO, false);
		app.LOGGER.setLevelEnabled(LoggingLevel.WARN, false);

		new CLIFrontend(app, System.in, System.out).start();
	}
}