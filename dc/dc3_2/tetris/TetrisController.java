package dc3_2.tetris;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import dc3_2.tetris.clazz.Tetrimino;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TetrisController implements Initializable {
	private static int BLOCK_SIZE = 20;

	private Thread gameThread;
	//private Thread controllerThread;
	//private final Object waiter = new Object();
	private GraphicsContext gc;
	private LinkedList<Tetrimino> tetriminos;
	private Tetrimino currentTetrimino;
	private int[][] field;
	private Color[][] fieldColors;

	private TetrisService tetrisService;

	@FXML
	private Canvas tetrisCanvas;
	@FXML
	private Canvas nextTetriminoCanvas;
	@FXML
	private Canvas tetriminoAfterNextCanvas;

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		// init
		tetrisService = new TetrisService();
		gc = tetrisCanvas.getGraphicsContext2D();
		currentTetrimino = tetrisService.getRandomTetrimino();
		tetriminos = new LinkedList<Tetrimino>() {
			{
				offer(tetrisService.getRandomTetrimino());
				offer(tetrisService.getRandomTetrimino());
			}
		};
		field = tetrisService.getTetris().getField();
		fieldColors = tetrisService.getTetris().getFieldColors();

		drowNextTetrimino(nextTetriminoCanvas, tetriminos.get(0));
		drowNextTetrimino(tetriminoAfterNextCanvas, tetriminos.get(1));

		gameThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					draw(field, fieldColors, currentTetrimino);
					if (tetrisService.isBottom(field, currentTetrimino)) {
						tetrisService.fixTetrimino(field, fieldColors, currentTetrimino);
						rollbackTetrimino(field, fieldColors, currentTetrimino);
						init();
						continue;
					}
					try {
						Thread.sleep(500);
						tetrisService.moveUnder();
						if (tetrisService.getY() == tetrisService.getFieldHeight()) {
							init();
						}
					} catch (final InterruptedException e) {
					}
				}
			}

			private void init() {
				tetrisService.initXYR();
				currentTetrimino = tetriminos.poll();
				tetriminos.offer(tetrisService.getRandomTetrimino());
				drowNextTetrimino(nextTetriminoCanvas, tetriminos.get(0));
				drowNextTetrimino(tetriminoAfterNextCanvas, tetriminos.get(1));
			}
		});
		gameThread.start();

		//		controllerThread = new Thread(new Runnable() {
		//			@Override
		//			public void run() {
		//				while (true) {
		//					try {
		//						waiter.wait();
		//						draw();
		//					} catch (final InterruptedException e) {
		//						// TODO 自動生成された catch ブロック
		//						e.printStackTrace();
		//					}
		//				}
		//			}
		//		});
		//		controllerThread.start();
	}

	public void initView(final Stage stage) {
		stage.getScene().setOnKeyPressed(e -> {
			tetrisService.handleTetorimino(e, currentTetrimino);
			draw(field, fieldColors, currentTetrimino);
		});
	}

	private void drowNextTetrimino(final Canvas canvas, final Tetrimino tetrimino) {
		drawInit(canvas);
		// draw tetrimino
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(tetrimino.getColor());
		for (int i = 0; i < tetrimino.getMino()[0].length; i++) {
			for (int j = 0; j < tetrimino.getMino()[0][i].length; j++) {
				if (tetrimino.getMino()[0][i][j] != 0) {
					gc.fillRect(BLOCK_SIZE * j, BLOCK_SIZE * i, BLOCK_SIZE, BLOCK_SIZE);
					gc.setStroke(Color.BLACK);
					gc.strokeRect(BLOCK_SIZE * j, BLOCK_SIZE * i, BLOCK_SIZE, BLOCK_SIZE);
				}
			}
		}
	}

	private void draw(final int[][] field, final Color[][] fieldColors, final Tetrimino tetorimino) {
		final int x = tetrisService.getX();
		final int y = tetrisService.getY();
		final int r = tetrisService.getR();
		drawInit(tetrisCanvas);
		drawField(field, fieldColors, x, y, r);
		drawFallingTetrimino(tetorimino, x, y, r);
	}

	private void rollbackTetrimino(final int[][] field, final Color[][] fieldColors, final Tetrimino tetorimino) {
		final int x = tetrisService.getX();
		final int y = tetrisService.getY();
		final int r = tetrisService.getR();
		drawInit(tetrisCanvas);
		drawField(field, fieldColors, x, y, r);
		drawFallingTetrimino(tetorimino, x, y - 1, r);
	}

	private void drawField(final int[][] field, final Color[][] fieldColors, final int x, final int y, final int r) {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] != 0) {
					gc.setFill(fieldColors[i][j]);
					gc.fillRect(BLOCK_SIZE * j, BLOCK_SIZE * i, BLOCK_SIZE, BLOCK_SIZE);
					gc.setStroke(Color.BLACK);
					gc.strokeRect(BLOCK_SIZE * j, BLOCK_SIZE * i, BLOCK_SIZE, BLOCK_SIZE);
				}
			}
		}
	}

	private void drawFallingTetrimino(final Tetrimino tetorimino, final int x, final int y, final int r) {
		gc.setFill(tetorimino.getColor());
		for (int i = 0; i < tetorimino.getMino()[r].length; i++) {
			for (int j = 0; j < tetorimino.getMino()[0][i].length; j++) {
				if (tetorimino.getMino()[r][i][j] != 0) {
					gc.fillRect(BLOCK_SIZE * (j + x), BLOCK_SIZE * (i + y), BLOCK_SIZE, BLOCK_SIZE);
					gc.setStroke(Color.BLACK);
					gc.strokeRect(BLOCK_SIZE * (j + x), BLOCK_SIZE * (i + y), BLOCK_SIZE, BLOCK_SIZE);
				}
			}
		}
	}

	private void drawInit(final Canvas canvas) {
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		// Init
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		// Set backgroundColor
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setStroke(Color.WHITE);
		gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
}
