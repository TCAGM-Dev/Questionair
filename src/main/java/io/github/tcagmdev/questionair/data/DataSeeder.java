package io.github.tcagmdev.questionair.data;

public interface DataSeeder {
	@FunctionalInterface
	interface EntryConsumer<T> {
		void accept(int id, T value);
	}
	void seed(EntryConsumer<Exam> putExam, EntryConsumer<LearningGoal> putGoal, EntryConsumer<Module> putModule, EntryConsumer<Question> putQuestion);
}