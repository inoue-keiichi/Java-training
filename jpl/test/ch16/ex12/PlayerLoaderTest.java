package ch16.ex12;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.net.URL;
import java.util.Enumeration;

import org.junit.jupiter.api.Test;

public class PlayerLoaderTest {
	@Test
	void findClassTest() {
		PlayerLoader loader = new PlayerLoader();
		try {
			Class<?> clazz = loader.findClass("/Users/user/git/Java-training/jpl/bin/ch16/ex12/RandomPlayer");
			assertEquals("ch16.ex12.RandomPlayer", clazz.getName());
		} catch (ClassNotFoundException e) {

		}
	}

	@Test
	void findResourceTest() {
		PlayerLoader loader = new PlayerLoader();
		URL actual = loader.findResource("/Users/user/git/Java-training/jpl/bin/ch16/ex12/resource/Guu.class");
		assertEquals("/Users/user/git/Java-training/jpl/bin/ch16/ex12/resource/Guu.class", actual.getPath());
	}

	@Test
	void findResourcesTest() {
		PlayerLoader loader = new PlayerLoader();
		Enumeration<URL> actual = loader.findResources("/Users/user/git/Java-training/jpl/bin/ch16/ex12/resource");
		int actualFileCount = 0;
		do {
			actual.nextElement();
			actualFileCount++;
		} while (actual.hasMoreElements());

		assertEquals(3, actualFileCount);
	}
}
