package ch06.ex07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.Test;

public class MainTest {

	@Test
	public void test() {
		final ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>() {
			{
				this.put("a", 1L);
				this.put("b", -300L);
				this.put("c", 300L);
				this.put("d", 345L);
				this.put("e", 200L);
			}
		};
		final String actual = Main.searchKeyHavingMaxValue(map);
		assertEquals("d", actual);
	}
}
