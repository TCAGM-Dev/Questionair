package io.github.tcagmdev.questionair.frontend;

import io.github.tcagmdev.questionair.App;
import io.github.tcagmdev.questionair.data.DataManager;
import io.github.tcagmdev.statesmith.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CLIFrontend implements Frontend {
	private final InputStream in;
	private final PrintStream out;

	private boolean running = false;

	private final App app;

	public CLIFrontend(App app, InputStream inputStream, PrintStream outputStream) {
		this.app = app;
		this.in = inputStream;
		this.out = outputStream;
	}

	private StateMachine<String> createStateMachine() {
		StateMachine<String> stateMachine = new StateMachine<>();
		StateNode<String> exitNode = stateMachine.addNode(_ -> this.running = false);

		StateNode<String> homeScreen = HomeScreenFactory.createNode(stateMachine, this.app, exitNode);

		stateMachine.setCurrentNode(homeScreen);

		stateMachine.setOnChange((prevNode, nextNode, v) -> System.out.println("\n\n"));

		return stateMachine;
	}

	public void start() {
		if (this.running) throw new IllegalStateException("Attempt to start already running CLIFrontend");

		this.running = true;

		StateMachine<String> uiStateMachine = this.createStateMachine();

		Scanner scanner = new Scanner(this.in);

		while (this.running) {
			uiStateMachine.consume(scanner.nextLine());
		}
	}
}