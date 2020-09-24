package dc3_2.tetris;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import dc3_2.tetris.TetriminoFactory;
import dc3_2.tetris.TetriminoFactory.TetriminoForm;
import dc3_2.tetris.TetrisController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ TetriminoFactory.class })
public class TetrisControllerTest {
	@InjectMocks
	private final TetrisController tetrisController = new TetrisController();
	private Canvas tetrisCanvas;
	private Canvas nextTetriminoCanvas;
	private Canvas tetriminoAfterNextCanvas;

	@Before
	public void setup()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final Field tetrisCanvasField = tetrisController.getClass().getDeclaredField("tetrisCanvas");
		tetrisCanvasField.setAccessible(true);
		tetrisCanvasField.set(tetrisController, new Canvas());
		tetrisCanvas = (Canvas) tetrisCanvasField.get(tetrisController);

		final Field nextTetriminoCanvasField = tetrisController.getClass().getDeclaredField("nextTetriminoCanvas");
		nextTetriminoCanvasField.setAccessible(true);
		nextTetriminoCanvasField.set(tetrisController, new Canvas());
		nextTetriminoCanvas = (Canvas) nextTetriminoCanvasField.get(tetrisController);

		final Field tetriminoAfterNextCanvasField = tetrisController.getClass()
				.getDeclaredField("tetriminoAfterNextCanvas");
		tetriminoAfterNextCanvasField.setAccessible(true);
		tetriminoAfterNextCanvasField.set(tetrisController, new Canvas());
		tetriminoAfterNextCanvas = (Canvas) tetriminoAfterNextCanvasField.get(tetrisController);

		MockitoAnnotations.initMocks(this);
		PowerMockito.spy(TetriminoFactory.class);
	}

	@Test
	public void test_initialize() {
		tetrisController.initialize(null, null);
	}

	@Test
	public void test_update() {
		PowerMockito.doReturn(TetriminoFactory.createTetrimino(TetriminoForm.I)).when(TetriminoFactory.class);
		TetriminoFactory.createRandomTetrimino();

		tetrisController.initialize(null, null);
		tetrisController.update(null, null);
		final GraphicsContext tetrisCanvasGC = nextTetriminoCanvas.getGraphicsContext2D();
		assertEquals(Color.BLACK, tetrisCanvasGC.getStroke());
		assertEquals(Color.CYAN, tetrisCanvasGC.getFill());
		final GraphicsContext nextTetriminoCanvasGC = nextTetriminoCanvas.getGraphicsContext2D();
		assertEquals(Color.BLACK, nextTetriminoCanvasGC.getStroke());
		assertEquals(Color.CYAN, nextTetriminoCanvasGC.getFill());
		final GraphicsContext tetriminoAfterNextCanvasGC = tetriminoAfterNextCanvas.getGraphicsContext2D();
		assertEquals(Color.BLACK, tetriminoAfterNextCanvasGC.getStroke());
		assertEquals(Color.CYAN, tetriminoAfterNextCanvasGC.getFill());
	}
}
