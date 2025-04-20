package io.github.tcagmdev.questionair.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ValueReference<T> implements Supplier<T>, Consumer<T> {
	private T value;

	public ValueReference() {}
	public ValueReference(T value) {
		this.value = value;
	}

	public void set(T value) {
		this.value = value;
	}
	public T get() {
		return this.value;
	}
	public String toString() {
		return String.valueOf(this.value);
	}
	public void accept(T value) {
		this.set(value);
	}
}