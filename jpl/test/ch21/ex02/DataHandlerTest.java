package ch21.ex02;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.Test;

public class DataHandlerTest {
	@Test
	public void normalTest() {
		File file = new File("/Users/inoue-keiichi/git/Java-training/bin/ch17/ex02/sample.class");
		final DataHandler dataHandler = new DataHandler();
		dataHandler.readFile(file);
		file = null;
		System.gc();
		assertEquals(null, dataHandler.getWeakHashMap()
				.get(new File("/Users/inoue-keiichi/git/Java-training/bin/ch17/ex02/sample.class")));
	}
}
