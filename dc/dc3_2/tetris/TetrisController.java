package dc3_2.tetris;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import dc3_2.tetris.clazz.Tetrimino;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TetrisController implements Initializable {
	private static int BLOCK_SIZE = 20;
	private static int X_INIT = 3;
	private static int Y_INIT = 0;
	private static int R_INIT = 0;
	private static int X_MIN = 0;
	//private static int X_MAX = 10;
	//private static int Y_MIN = 0;
	//private static int Y_MAX = 20;

	private Thread gameThread;
	//private Thread controllerThread;
	//private final Object waiter = new Object();
	private GraphicsContext gc;
	private int x;
	private int y;
	private int r;
	private LinkedList<Tetrimino> tetriminos;
	private Tetrimino currentTetrimino;
	private int[][] field;
	private int fieldWidth;
	private int fieldHeight;

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
		x = X_INIT;
		y = Y_INIT;
		currentTetrimino = tetrisService.getRandomTetrimino();
		tetriminos = new LinkedList<Tetrimino>() {
			{
				offer(tetrisService.getRandomTetrimino());
				offer(tetrisService.getRandomTetrimino());
			}
		};
		field = tetrisService.getTetris().getField();
		fieldWidth = field[0].length;
		fieldHeight = field.length;

		drowNextTetrimino(nextTetriminoCanvas, tetriminos.get(0));
		drowNextTetrimino(tetriminoAfterNextCanvas, tetriminos.get(1));

		gameThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					draw(currentTetrimino);
					try {
						Thread.sleep(500);
						y++;
						if (y == fieldHeight) {
							x = X_INIT;
							y = Y_INIT;
							r = R_INIT;
							currentTetrimino = tetriminos.poll();
							tetriminos.offer(tetrisService.getRandomTetrimino());
							drowNextTetrimino(nextTetriminoCanvas, tetriminos.get(0));
							drowNextTetrimino(tetriminoAfterNextCanvas, tetriminos.get(1));
						}
					} catch (final InterruptedException e) {
					}
				}
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
			handleTetorimino(e);
		});
	}

	private void handleTetorimino(final KeyEvent e) {
		switch (e.getCode()) {
		case LEFT:
		case KP_LEFT:
			x--;
			if (isOutsideX(this.currentTetrimino)) {
				x++;
			}
			break;
		case RIGHT:
		case KP_RIGHT:
			x++;
			if (isOutsideX(this.currentTetrimino)) {
				x--;
			}
			break;
		case DOWN:
		case KP_DOWN:
			break;
		case SHIFT:
			r = (r + 1) % this.currentTetrimino.getMino().length;
			break;
		default:
			break;
		}
		draw(this.currentTetrimino);
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

	private boolean isOutsideX(final Tetrimino tetorimino) {
		for (int i = 0; i < tetorimino.getMino()[r].length; i++) {
			for (int j = 0; j < tetorimino.getMino()[0][i].length; j++) {
				if (tetorimino.getMino()[r][i][j] != 0 && (j + x < 0 || j + x > fieldWidth - 1)) {
					return true;
				}
			}
		}
		return false;
	}

	private void draw(final Tetrimino tetorimino) {
		drawInit(tetrisCanvas);
		// write falling tetrimino
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
