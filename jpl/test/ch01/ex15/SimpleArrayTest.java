package ch01.ex15;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import org.junit.jupiter.api.Test;

public class SimpleArrayTest {
	// nameとvalueを追加できるかテスト
	@Test
	void addTest() {
		SimpleArray array = new SimpleArray();
		array.add("Taro", 100);
		array.add("Hanako", 50);
		array.add("Tanaka", 70);
		
		assertEquals(3, array.getNames().length);
		assertEquals(100, array.find("Taro"));
		assertEquals(50, array.find("Hanako"));
		assertEquals(70, array.find("Tanaka"));
	}
	
	// namesとvaluesの最後の要素を削除できるかテスト
		@Test
		void removeTest() {
			SimpleArray array = new SimpleArray();
			array.add("Taro", 100);
			array.add("Hanako", 50);
			array.add("Tanaka", 70);
			array.remove();
			
			assertEquals(2, array.getNames().length);
			assertEquals(100, array.find("Taro"));
			assertEquals(50, array.find("Hanako"));
		}
	
}
