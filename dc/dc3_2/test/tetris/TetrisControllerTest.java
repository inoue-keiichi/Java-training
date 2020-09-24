package dc3_2.test.tetris;

import org.junit.Test;

import dc3_2.tetris.TetrisController;

public class TetrisControllerTest {

	@Test
	public void test() {
		final TetrisController tetrisController = new TetrisController();
		tetrisController.initialize(null, null);
	}
}
