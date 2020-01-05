package ch14.ex02;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class PrintServerTest {
	@Test
	void test() {
		PrintServer printServer = new PrintServer();
		printServer.print(new PrintJob());

		try {
			printServer.run();
			fail();
		} catch (RuntimeException e) {
			assertEquals("別のスレッドからrunメソッドを実行しないでください", e.getMessage());
		}
	}
}