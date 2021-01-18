package dc3_2.tetris;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dc3_2.frame.game.tetris.TetriminoFactory;
import dc3_2.frame.game.tetris.TetrisService;
import dc3_2.frame.game.tetris.TetriminoFactory.TetriminoForm;
import dc3_2.frame.game.tetris.clazz.Tetrimino;
import dc3_2.frame.game.tetris.clazz.TetrisField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class TetrisServiceTest {
	private final TetrisService tetrisService = new TetrisService();;
	private Tetrimino tetriminoO;
	private Tetrimino tetriminoJ;
	private TetrisField tetrisField;

	@Before
	public void setup() {
		tetriminoO = TetriminoFactory.createTetrimino(TetriminoForm.O);
		tetriminoJ = TetriminoFactory.createTetrimino(TetriminoForm.J);
		tetrisField = new TetrisField();
	}

	@Test
	public void handleTetorimino_normalTest_speedUpAndDown() {
		final KeyEvent e = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.DOWN, false, false, false, false);
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		assertEquals(100, tetriminoO.getSpeed());

		final KeyEvent e2 = new KeyEvent(KeyEvent.KEY_RELEASED, null, null, KeyCode.DOWN, false, false, false,
				false);
		tetrisService.handleTetorimino(e2, tetriminoO, tetrisField.getColors());
		assertEquals(500, tetriminoO.getSpeed());

		final KeyEvent e3 = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.KP_DOWN, false, false, false, false);
		tetrisService.handleTetorimino(e3, tetriminoO, tetrisField.getColors());
		assertEquals(100, tetriminoO.getSpeed());

		final KeyEvent e4 = new KeyEvent(KeyEvent.KEY_RELEASED, null, null, KeyCode.KP_DOWN, false, false, false,
				false);
		tetrisService.handleTetorimino(e4, tetriminoO, tetrisField.getColors());
		assertEquals(500, tetriminoO.getSpeed());
	}

	@Test
	public void handleTetorimino_normalTest_moveLeft() {
		final KeyEvent e = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.LEFT, false, false, false, false);
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		assertEquals(2, tetriminoO.getX());
	}

	@Test
	public void handleTetorimino_normalTest_moveLeft_limit() {
		final KeyEvent e = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.LEFT, false, false, false, false);

		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		assertEquals(-1, tetriminoO.getX());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		assertEquals(-1, tetriminoO.getX());
	}

	@Test
	public void handleTetorimino_normalTest_moveLeft_KP() {
		final KeyEvent e = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.KP_LEFT, false, false, false, false);
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		assertEquals(2, tetriminoO.getX());
	}

	@Test
	public void handleTetorimino_normalTest_moveLeft_limit_KP() {
		final KeyEvent e = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.KP_LEFT, false, false, false, false);

		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		assertEquals(-1, tetriminoO.getX());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		assertEquals(-1, tetriminoO.getX());
	}

	@Test
	public void handleTetorimino_normalTest_moveRight() {
		final KeyEvent e = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.RIGHT, false, false, false, false);
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		assertEquals(4, tetriminoO.getX());
	}

	@Test
	public void handleTetorimino_normalTest_moveRight_limit() {
		final KeyEvent e = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.RIGHT, false, false, false, false);

		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		assertEquals(7, tetriminoO.getX());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		assertEquals(7, tetriminoO.getX());
	}

	@Test
	public void handleTetorimino_normalTest_moveRight_KP() {
		final KeyEvent e = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.KP_RIGHT, false, false, false, false);
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		assertEquals(4, tetriminoO.getX());
	}

	@Test
	public void handleTetorimino_normalTest_moveRight_limit_KP() {
		final KeyEvent e = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.KP_RIGHT, false, false, false, false);

		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		assertEquals(7, tetriminoO.getX());
		tetrisService.handleTetorimino(e, tetriminoO, tetrisField.getColors());
		assertEquals(7, tetriminoO.getX());
	}

	@Test
	public void handleTetorimino_normalTest_rorate() {
		assertEquals(0, tetriminoJ.getRotationPosition());
		final KeyEvent e = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.SHIFT, false, false, false, false);
		tetrisService.handleTetorimino(e, tetriminoJ, tetrisField.getColors());
		assertEquals(1, tetriminoJ.getRotationPosition());
		tetrisService.handleTetorimino(e, tetriminoJ, tetrisField.getColors());
		assertEquals(2, tetriminoJ.getRotationPosition());
		tetrisService.handleTetorimino(e, tetriminoJ, tetrisField.getColors());
		assertEquals(3, tetriminoJ.getRotationPosition());
		tetrisService.handleTetorimino(e, tetriminoJ, tetrisField.getColors());
		assertEquals(0, tetriminoJ.getRotationPosition());
	}

	@Test
	public void handleTetorimino_normalTest_rotate_limit() {
		final KeyEvent eRotate = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.SHIFT, false, false, false,
				false);
		tetrisService.handleTetorimino(eRotate, tetriminoJ, tetrisField.getColors());
		final KeyEvent eLeft = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.LEFT, false, false, false, false);
		tetrisService.handleTetorimino(eLeft, tetriminoJ, tetrisField.getColors());
		tetrisService.handleTetorimino(eLeft, tetriminoJ, tetrisField.getColors());
		tetrisService.handleTetorimino(eLeft, tetriminoJ, tetrisField.getColors());
		tetrisService.handleTetorimino(eLeft, tetriminoJ, tetrisField.getColors());
		tetrisService.handleTetorimino(eLeft, tetriminoJ, tetrisField.getColors());
		tetrisService.handleTetorimino(eRotate, tetriminoJ, tetrisField.getColors());
		// desire not to throw error.
	}

	@Test
	public void handleTetorimino_normalTest_isOutsideX() {
		// inside
		assertTrue(!tetrisService.isOutsideX(tetrisField.getColors(), tetriminoO));
		// outside left
		tetriminoO.moveLeft();
		tetriminoO.moveLeft();
		tetriminoO.moveLeft();
		tetriminoO.moveLeft();
		tetriminoO.moveLeft();
		assertTrue(tetrisService.isOutsideX(tetrisField.getColors(), tetriminoO));
		// inside
		tetriminoO.moveRight();
		tetriminoO.moveRight();
		tetriminoO.moveRight();
		tetriminoO.moveRight();
		tetriminoO.moveRight();
		tetriminoO.moveRight();
		tetriminoO.moveRight();
		tetriminoO.moveRight();
		tetriminoO.moveRight();
		System.out.println(tetriminoO.getX());
		assertTrue(!tetrisService.isOutsideX(tetrisField.getColors(), tetriminoO));
		// outside right
		tetriminoO.moveRight();
		assertTrue(tetrisService.isOutsideX(tetrisField.getColors(), tetriminoO));
	}

	@Test
	public void handleTetorimino_normalTest_isBottom() {
		assertTrue(!tetrisService.isBottom(tetrisField.getColors(), tetriminoO));
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		tetriminoO.moveDown();
		assertTrue(!tetrisService.isBottom(tetrisField.getColors(), tetriminoO));
		tetriminoO.moveDown();
		assertTrue(tetrisService.isBottom(tetrisField.getColors(), tetriminoO));
	}

	@Test
	public void clearLine_normalTest() {
		final int lastIndex = tetrisField.getColors().length - 1;

		// clear one line.
		for (int i = 0; i < tetrisField.getColors()[lastIndex].length; i++) {
			tetrisField.getColors()[lastIndex][i] = Color.RED;
		}
		tetrisService.clearLine(tetrisField.getColors());
		for (int j = 0; j < tetrisField.getColors()[0].length; j++) {
			assertEquals(null, tetrisField.getColors()[lastIndex][j]);
		}

		// clear two lines.
		for (int i = lastIndex; i > lastIndex - 2; i--) {
			for (int j = 0; j < tetrisField.getColors()[0].length; j++) {
				tetrisField.getColors()[i][j] = Color.RED;
			}
		}
		tetrisService.clearLine(tetrisField.getColors());
		for (int i = lastIndex; i > lastIndex - 2; i--) {
			for (int j = 0; j < tetrisField.getColors()[0].length; j++) {
				assertEquals(null, tetrisField.getColors()[i][j]);
			}
		}
	}

	@Test
	public void clearLine_normalTest_remainBlock() {
		final int lastIndex = tetrisField.getColors().length - 1;

		for (int i = 0; i < tetrisField.getColors()[lastIndex].length; i++) {
			tetrisField.getColors()[lastIndex][i] = Color.RED;
		}
		tetrisField.getColors()[lastIndex - 1][0] = Color.RED;
		tetrisService.clearLine(tetrisField.getColors());
		for (int i = 0; i < tetrisField.getColors()[0].length; i++) {
			if (i == 0) {
				assertEquals(Color.RED, tetrisField.getColors()[lastIndex][i]);
			} else {
				assertEquals(null, tetrisField.getColors()[lastIndex][i]);
			}
		}
	}

	@Test
	public void clearLine_normalTest_topLine() {
		for (int i = 0; i < tetrisField.getColors()[0].length; i++) {
			tetrisField.getColors()[0][i] = Color.RED;
		}
		tetrisService.clearLine(tetrisField.getColors());
		for (int i = 0; i < tetrisField.getColors()[0].length; i++) {
			assertEquals(null, tetrisField.getColors()[0][i]);
		}
	}

	@Test
	public void isOverlap_normalTest() {
		for (int i = 0; i < tetrisField.getColors().length; i++) {
			for (int j = 0; j < tetrisField.getColors()[0].length; j++) {
				tetrisField.getColors()[i][j] = Color.BLACK;
			}
		}
		tetrisField.getColors()[0][4] = null;
		tetrisField.getColors()[0][5] = null;
		tetrisField.getColors()[1][4] = null;
		tetrisField.getColors()[1][5] = null;
		assertTrue(!tetrisService.isOverlap(tetrisField.getColors(), tetriminoO));

		for (int i = 0; i < tetrisField.getColors().length; i++) {
			for (int j = 0; j < tetrisField.getColors()[0].length; j++) {
				tetrisField.getColors()[i][j] = Color.BLACK;
			}
		}
		assertTrue(tetrisService.isOverlap(tetrisField.getColors(), tetriminoO));
	}
}
