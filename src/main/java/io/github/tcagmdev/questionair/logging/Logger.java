package io.github.tcagmdev.questionair.logging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {
	private final String sourceName;
	private final Map<LoggingLevel, Boolean> levelsEnabled = new HashMap<>();

	public Logger(String sourceName) {
		this.sourceName = sourceName;

		for (LoggingLevel level : LoggingLevel.values()) this.levelsEnabled.put(level, true);
	}
	public Logger() {
		this(null);
	}

	public void setLevelEnabled(LoggingLevel level, boolean enabled) {
		this.levelsEnabled.put(level, enabled);
	}

	public void log(LoggingLevel level, String message) {
		if (!this.levelsEnabled.get(level)) return;

		List<String> infoSegments = new ArrayList<>();

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		int parentTraceIndex = 1;
		while (stackTrace[parentTraceIndex].getClassName().equals(Logger.class.getName())) parentTraceIndex++;
		StackTraceElement parentTrace = stackTrace[parentTraceIndex];
		infoSegments.add(String.format("[%s:%d]", parentTrace.getClassName(), parentTrace.getLineNumber()));

		infoSegments.add(String.format("[%s]", level.name()));

		if (this.sourceName != null) infoSegments.add(String.format("(%s)", this.sourceName));

		String logMessage = String.join(" ", infoSegments) + ": " + message;

		level.getOutputStream().println(logMessage);
	}
	public void log(LoggingLevel level, String format, Object... args) {
		this.log(level, String.format(format, args));
	}

	public void debug(String message) {
		this.log(LoggingLevel.DEBUG, message);
	}
	public void debug(String format, Object... args) {
		this.log(LoggingLevel.DEBUG, format, args);
	}

	public void info(String message) {
		this.log(LoggingLevel.INFO, message);
	}
	public void info(String format, Object... args) {
		this.log(LoggingLevel.INFO, format, args);
	}

	public void warn(String message) {
		this.log(LoggingLevel.WARN, message);
	}
	public void warn(String format, Object... args) {
		this.log(LoggingLevel.WARN, format, args);
	}
	public void warn(Throwable e) {
		this.log(LoggingLevel.WARN, e.toString());
	}

	public void error(String message) {
		this.log(LoggingLevel.ERROR, message);
	}
	public void error(String format, Object... args) {
		this.log(LoggingLevel.ERROR, format, args);
	}
	public void error(Throwable e) {
		this.log(LoggingLevel.ERROR, e.toString());
	}

	public void fatal(String message) {
		this.log(LoggingLevel.FATAL, message);
	}
	public void fatal(String format, Object... args) {
		this.log(LoggingLevel.FATAL, format, args);
	}
	public void fatal(Throwable e) {
		this.log(LoggingLevel.FATAL, e.toString());
	}
}