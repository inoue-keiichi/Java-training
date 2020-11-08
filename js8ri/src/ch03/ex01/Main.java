package ch03.ex01;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class Main {
	public static void main(final String[] args) throws SecurityException, IOException {
		final EnhancedLogger enhancedLogger = new EnhancedLogger("hoge");
		enhancedLogger.getLogger().setLevel(Level.FINEST);
		final Handler handler = new FileHandler("./src/resource/log/sample.log");
		handler.setFormatter(new SimpleFormatter());
		enhancedLogger.getLogger().addHandler(handler);

		final String[] a = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten" };
		final int i = 10;
		enhancedLogger.logIf(Level.FINEST, () -> i == 9, () -> "a[10]" + a[9]);
		enhancedLogger.logIf(Level.FINEST, () -> i == 10, () -> "a[10]" + a[10]);
	}
}
