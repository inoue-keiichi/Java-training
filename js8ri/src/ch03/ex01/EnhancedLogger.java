package ch03.ex01;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnhancedLogger {
	private final Logger logger;

	public EnhancedLogger(final String name) {
		logger = Logger.getLogger(name);
	}

	public Logger getLogger() {
		return logger;
	}

	public void logIf(final Level level, final BooleanSupplier loggable, final Supplier<String> message) {
		if (!logger.isLoggable(level) || !loggable.getAsBoolean()) {
			return;
		}
		logger.info(message.get());
	}
}
