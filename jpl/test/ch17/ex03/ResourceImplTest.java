package ch17.ex03;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ResourceImplTest {
	@Test
	void test() {
		final Object obj = new Object();
		final ResourceManager resourceManager = new ResourceManager();

		try {
			final Resource resource = resourceManager.getResource(obj);
			resource.use(new Object(), new String());
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("wrong key", e.getMessage());
		}
	}
}