package io.github.tcagmdev.questionair.frontend;

import io.github.tcagmdev.statesmith.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CLIFrontend implements Frontend {
	private final InputStream in;
	private final PrintStream out;

	private boolean running = false;

	public CLIFrontend(InputStream inputStream, PrintStream outputStream) {
		this.in = inputStream;
		this.out = outputStream;
	}

	private StateMachine<String> createStateMachine() {
		StateMachine<String> stateMachine = new StateMachine<>();

		StateNode<String> homeScreen = stateMachine.addNode();
		StateNode<String> optionsScreen = stateMachine.addNode(_ -> {
			System.out.println();
			stateMachine.setCurrentNode(homeScreen);
		});
		StateNode<String> exitNode = stateMachine.addNode(_ -> this.running = false);

		stateMachine.setCurrentNode(homeScreen);

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

	public static void main(String[] args) {
		new CLIFrontend(System.in, System.out).start();
	}
}