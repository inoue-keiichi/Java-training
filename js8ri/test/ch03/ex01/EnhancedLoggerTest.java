package ch03.ex01;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

import org.junit.Test;

public class EnhancedLoggerTest {

	@Test
	public void test() throws SecurityException, IOException {
		final EnhancedLogger enhancedLogger = new EnhancedLogger("hoge");
		enhancedLogger.getLogger().setLevel(Level.INFO);
		final Handler handler = new FileHandler("./test/resource/log/sample.log");
		handler.setFormatter(new SimpleFormatter());
		enhancedLogger.getLogger().addHandler(handler);

		final String a = "hoge";
		final String b = "fuga";
		// INFOより低いレベルはログに出ない
		enhancedLogger.logIf(Level.FINEST, () -> true, () -> "truetrue");
		// 判定がtrueならログに出る
		enhancedLogger.logIf(Level.INFO, () -> a.equals("hoge"), () -> "hogehoge");
		enhancedLogger.logIf(Level.INFO, () -> b.equals("fuga"), () -> "fugafuga");
		// 判定がfalseならログに出ない
		enhancedLogger.logIf(Level.INFO, () -> a.equals("piyo"), () -> "piyopiyo");

		int actualHoge = 0;
		int actualFuga = 0;
		try (final FileReader fr = new FileReader("./test/resource/log/sample.log");
				final BufferedReader br = new BufferedReader(fr);) {
			String line = br.readLine();
			while (line != null) {
				if (line.matches(".*hogehoge.*")) {
					actualHoge++;
				} else if (line.matches(".*fugafuga.*")) {
					actualFuga++;
				}
				line = br.readLine();
			}

		}
		assertEquals(1, actualHoge);
		assertEquals(1, actualFuga);
		// assertEquals(null, br.readLine());
	}
}
