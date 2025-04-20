package io.github.tcagmdev.questionair.util;

public class ValueReference<T> {
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
}