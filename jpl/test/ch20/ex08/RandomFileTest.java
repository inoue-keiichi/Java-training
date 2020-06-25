package ch20.ex08;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class RandomFileTest {
	@Test
	public void normalTest() throws FileNotFoundException, IOException {
		File file = new File("/Users/inoue-keiichi/git/Java-training/jpl/test/resources/entry.txt");
		File table = RandomFile.createTableFile(file);

		List<String> result = RandomFile.getEntryRandomly(table, file);
		assertTrue(result.size() > 0);
		assertTrue(!result.contains("%%"));
		assertTrue(result.get(0).matches("name=.*"));
		assertTrue(result.get(1).matches("pet=.*"));
		assertTrue(result.get(2).matches("food=.*"));
	}
}
