package io.github.tcagmdev.questionair.logging;

import java.io.PrintStream;

public enum LoggingLevel {
	DEBUG(System.out), INFO(System.out), WARN(System.out), ERROR(System.err), FATAL(System.err);

	private final PrintStream out;

	LoggingLevel(PrintStream outputStream) {
		this.out = outputStream;
	}

	public PrintStream getOutputStream() {
		return this.out;
	}
}