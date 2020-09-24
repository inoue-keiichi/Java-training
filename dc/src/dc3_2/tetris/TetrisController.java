package dc3_2.tetris;

import java.net.URL;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import dc3_2.tetris.clazz.Tetrimino;
import dc3_2.tetris.clazz.TetrisField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TetrisController implements Initializable, Observer {

	private Thread gameThread;
	private TetrisRunnable tetrisRunnable;
	//private GraphicsContext gc;
	private LinkedList<Tetrimino> tetriminos;
	private Tetrimino currentTetrimino;
	private TetrisField tetrisField;

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
		currentTetrimino = TetriminoFactory.createRandomTetrimino();
		tetriminos = new LinkedList<Tetrimino>() {
			{
				offer(TetriminoFactory.createRandomTetrimino());
				offer(TetriminoFactory.createRandomTetrimino());
			}
		};
		tetrisField = new TetrisField();
		tetrisRunnable = new TetrisRunnable(currentTetrimino);
		tetrisRunnable.addObserver(this);
		gameThread = new Thread(tetrisRunnable);
		gameThread.start();

		drowNextTetrimino(nextTetriminoCanvas, tetriminos.get(0));
		drowNextTetrimino(tetriminoAfterNextCanvas, tetriminos.get(1));
	}

	public void initView(final Stage stage) {
		stage.getScene().setOnKeyPressed(e -> {
			tetrisService.handleTetorimino(e, currentTetrimino, tetrisField.getColors());
			initCanvas(tetrisCanvas);
			draw(tetrisCanvas.getGraphicsContext2D(), tetrisField.getColors(), currentTetrimino);
		});

		stage.getScene().setOnKeyReleased(e -> {
			tetrisService.handleTetorimino(e, currentTetrimino, tetrisField.getColors());
			initCanvas(tetrisCanvas);
			draw(tetrisCanvas.getGraphicsContext2D(), tetrisField.getColors(), currentTetrimino);
		});
	}

	@Override
	public void update(final Observable o, final Object arg) {
		if (tetrisService.isOverlap(tetrisField.getColors(), currentTetrimino)) {
			System.out.println("Hello!!");
			return;
		}
		if (tetrisService.isBottom(tetrisField.getColors(), currentTetrimino)) {
			tetrisField.fixTetrimino(currentTetrimino);
			rollbackTetrimino(tetrisCanvas.getGraphicsContext2D(), tetrisField.getColors(),
					currentTetrimino);
			currentTetrimino = updateTetriminos(tetriminos);
			drowNextTetrimino(nextTetriminoCanvas, tetriminos.get(0));
			drowNextTetrimino(tetriminoAfterNextCanvas, tetriminos.get(1));
			tetrisRunnable.setTetrimino(currentTetrimino);
		}
		initCanvas(tetrisCanvas);
		draw(tetrisCanvas.getGraphicsContext2D(), tetrisField.getColors(), currentTetrimino);
		System.out.println(Thread.currentThread().getName());
		System.out.println(Thread.currentThread().getName());
		System.out.println(Thread.currentThread().getName());
		try {
			this.tetrisRunnable.waitObject.suspend();
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		this.tetrisRunnable.waitObject.resume();
	}

	private void drowNextTetrimino(final Canvas canvas, final Tetrimino tetrimino) {
		initCanvas(canvas);
		// draw tetrimino
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(tetrimino.getColor());
		for (int i = 0; i < tetrimino.getMino()[0].length; i++) {
			for (int j = 0; j < tetrimino.getMino()[0][i].length; j++) {
				if (tetrimino.getMino()[0][i][j] != 0) {
					gc.fillRect(TetrisField.BLOCK_SIZE * j, TetrisField.BLOCK_SIZE * i, TetrisField.BLOCK_SIZE,
							TetrisField.BLOCK_SIZE);
					gc.setStroke(Color.BLACK);
					gc.strokeRect(TetrisField.BLOCK_SIZE * j, TetrisField.BLOCK_SIZE * i, TetrisField.BLOCK_SIZE,
							TetrisField.BLOCK_SIZE);
				}
			}
		}
	}

	private void draw(final GraphicsContext gc, final Color[][] fieldColors, final Tetrimino tetorimino) {
		drawField(gc, fieldColors);
		drawFallingTetrimino(gc, tetorimino);
	}

	private void rollbackTetrimino(final GraphicsContext gc, final Color[][] fieldColors, final Tetrimino tetrimino) {
		tetrimino.moveUp();
		draw(gc, fieldColors, tetrimino);
	}

	private void drawField(final GraphicsContext gc, final Color[][] fieldColors) {
		tetrisService.clearLine(fieldColors);
		for (int i = 0; i < TetrisField.HEIGHT; i++) {
			for (int j = 0; j < fieldColors[i].length; j++) {
				if (fieldColors[i][j] != null) {
					gc.setFill(fieldColors[i][j]);
					gc.fillRect(TetrisField.BLOCK_SIZE * j, TetrisField.BLOCK_SIZE * i, TetrisField.BLOCK_SIZE,
							TetrisField.BLOCK_SIZE);
					gc.setStroke(Color.BLACK);
					gc.strokeRect(TetrisField.BLOCK_SIZE * j, TetrisField.BLOCK_SIZE * i, TetrisField.BLOCK_SIZE,
							TetrisField.BLOCK_SIZE);
				}
			}
		}
	}

	private void drawFallingTetrimino(final GraphicsContext gc, final Tetrimino tetrimino) {
		final int x = tetrimino.getX();
		final int y = tetrimino.getY();
		final int r = tetrimino.getRotationPosition();
		gc.setFill(tetrimino.getColor());
		for (int i = 0; i < tetrimino.getMino()[r].length; i++) {
			for (int j = 0; j < tetrimino.getMino()[0][i].length; j++) {
				if (tetrimino.getMino()[r][i][j] != 0) {
					gc.fillRect(TetrisField.BLOCK_SIZE * (j + x), TetrisField.BLOCK_SIZE * (i + y),
							TetrisField.BLOCK_SIZE, TetrisField.BLOCK_SIZE);
					gc.setStroke(Color.BLACK);
					gc.strokeRect(TetrisField.BLOCK_SIZE * (j + x), TetrisField.BLOCK_SIZE * (i + y),
							TetrisField.BLOCK_SIZE, TetrisField.BLOCK_SIZE);
				}
			}
		}
	}

	private Tetrimino updateTetriminos(final LinkedList<Tetrimino> tetriminos) {
		tetriminos.offer(TetriminoFactory.createRandomTetrimino());
		return tetriminos.poll();
	}

	private void initCanvas(final Canvas canvas) {
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
