package ch21.ex07;

import static org.junit.Assert.*;

import org.junit.Test;

public class StackTest {

	@Test
	public void emptyTest() {
		Stack<String> stack = new Stack<>();
		assertEquals(true, stack.empty());
	}

	@Test
	public void pushTest() {
		Stack<String> stack = new Stack<>();
		assertEquals("Brazil", stack.push("Brazil"));
		assertEquals(false, stack.empty());
		stack.push("Egypt");
		assertEquals(1, stack.search("Brazil"));
	}

	@Test
	public void searchTest() {
		Stack<String> stack = new Stack<>();
		assertEquals(-1, stack.search("Brazil"));
		stack.push("Brazil");
		stack.push("Egypt");
		stack.push("Japan");
		assertEquals(0, stack.search("Japan"));
		assertEquals(1, stack.search("Egypt"));
		assertEquals(2, stack.search("Brazil"));
	}

	@Test
	public void searchTest_hasSameItem() {
		Stack<String> stack = new Stack<>();
		stack.push("Egypt");
		stack.push("Brazil");
		stack.push("Egypt");
		stack.push("Japan");
		assertEquals(1, stack.search("Egypt"));
	}

	@Test
	public void peekTest() {
		Stack<String> stack = new Stack<>();
		stack.push("Brazil");
		stack.push("Egypt");
		stack.push("Japan");
		assertEquals("Japan", stack.peek());
		assertEquals("Japan", stack.peek());
		assertEquals(0, stack.search("Japan"));
		assertEquals(1, stack.search("Egypt"));
		assertEquals(2, stack.search("Brazil"));
	}

	@Test
	public void popTest() {
		Stack<String> stack = new Stack<>();
		stack.push("Brazil");
		stack.push("Egypt");
		stack.push("Japan");
		assertEquals("Japan", stack.pop());
		assertEquals("Egypt", stack.pop());
		assertEquals("Brazil", stack.pop());
		assertEquals(true, stack.empty());
	}
}
