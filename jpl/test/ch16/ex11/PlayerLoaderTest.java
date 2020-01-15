package ch16.ex11;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static ch16.ex11.Game.GameResult.*;

import org.junit.jupiter.api.Test;

public class PlayerLoaderTest {
	@Test
	void test() {
		PlayerLoader loader = new PlayerLoader();
		try {
			Class<?> clazz = loader.findClass("/Users/user/git/Java-training/jpl/bin/ch16/ex11/RandomPlayer");
			assertNotNull(clazz);
			assertEquals("ch16.ex11.RandomPlayer", clazz.getName());
		} catch (ClassNotFoundException e) {

		}
	}
}
