package io.github.tcagmdev.questionair;

import io.github.tcagmdev.questionair.data.DataManager;
import io.github.tcagmdev.questionair.logging.Logger;

public class App {
	public final DataManager DATA;
	public final Logger LOGGER;

	public App(DataManager dataManager, String name) {
		this.DATA = dataManager;
		this.LOGGER = new Logger(name);
	}
}