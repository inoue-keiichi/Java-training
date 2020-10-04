package ch01.ex09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Collection2<T> extends Collection<T> {
	List<Object> elementData = new ArrayList<>();

	default void forEachIf(final Consumer<T> action, final Predicate<T> filter) {
		Objects.requireNonNull(action);
		@SuppressWarnings("unchecked")
		final T[] elementData = (T[]) Collection2.elementData.toArray();
		for (int i = 0; i < Collection2.elementData.size(); i++) {
			if (filter.test(elementData[i])) {
				action.accept(elementData[i]);
			}
		}
	};
}
