package ch17.ex04;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ResourceManagerTest {
	@Test
	void test() {
		final ResourceManager resourceManager = new ResourceManager();
		for(int i=0;i<1000000;i++) {	
			resourceManager.getResource(new Object());
		}
		try {
			resourceManager.shutdown();
			assertEquals(null, resourceManager.queue.poll());
		} catch (IllegalArgumentException e) {
		}
	}
}