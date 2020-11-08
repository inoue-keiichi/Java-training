package ch03.ex21;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

public class MainTest {
	@Test
	public void test() throws ExecutionException, InterruptedException, TimeoutException {
		final Future<String> future = new Future<String>() {
			@Override
			public boolean cancel(final boolean mayInterruptIfRunning) {
				return false;
			}

			@Override
			public boolean isCancelled() {
				return false;
			}

			@Override
			public boolean isDone() {
				return false;
			}

			@Override
			public String get() throws InterruptedException, ExecutionException {
				return "KochaKaden";
			}

			@Override
			public String get(final long timeout, final TimeUnit unit)
					throws InterruptedException, ExecutionException, TimeoutException {
				return "KochaKaden";
			}
		};
		final Future<Integer> actual = Main.map(future, (str) -> str.length());
		assertEquals(10, actual.get().intValue());
		assertEquals(10, actual.get(100, TimeUnit.SECONDS).intValue());
	}
}
