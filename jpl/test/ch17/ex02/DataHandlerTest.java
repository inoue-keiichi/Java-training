package ch17.ex02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;

import org.junit.jupiter.api.Test;

public class DataHandlerTest {
	@Test
	void test() {
		File file = new File("/Users/user/git/Java-training/jpl/bin/ch17/ex02/sample.class");
		final DataHandler dataHandler = new DataHandler();
		dataHandler.readFile(file);
		file = null;
		System.gc();
		assertEquals(null, dataHandler.getLastFile().get());
	}
}
