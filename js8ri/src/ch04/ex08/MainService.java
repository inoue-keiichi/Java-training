package ch04.ex08;

import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ObservableValue;

public class MainService {
	public static <T, R> ObservableValue<R> observe(final Function<T, R> f, final ObservableValue<T> t) {
		return new ObjectBinding<R>() {
			{
				bind(t);
			}

			@Override
			protected R computeValue() {
				return f.apply(t.getValue());
			}
		};
	}

	public static <T, U, R> ObservableValue<R> observe(final BiFunction<T, U, R> f, final ObservableValue<T> t,
			final ObservableValue<U> u) {
		return new ObjectBinding<R>() {
			{
				bind(t, u);
			}

			@Override
			protected R computeValue() {
				return f.apply(t.getValue(), u.getValue());
			}
		};
	}
}
