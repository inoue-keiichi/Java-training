package dc3_2.frame.game.tetris;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
//import java.util.Observable;
//import java.util.Observer;
import java.util.ResourceBundle;

import dc3_2.frame.game.tetris.clazz.Tetrimino;
import dc3_2.frame.game.tetris.clazz.TetrisField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TetrisController implements Initializable, PropertyChangeListener {
	private final int SCORE_BASIS_VALUE = 100;
	private int clearLineCounts = 0;

	private Thread gameThread;
	private TetrisRunnable tetrisRunnable;
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
	@FXML
	private Label levelValue;
	@FXML
	private Label scoreValue;

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		// init
		tetrisService = new TetrisService();
		currentTetrimino = TetriminoFactory.createRandomTetrimino();
		tetriminos = new LinkedList<Tetrimino>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				offer(TetriminoFactory.createRandomTetrimino());
				offer(TetriminoFactory.createRandomTetrimino());
			}
		};
		tetrisField = new TetrisField();
		tetrisRunnable = new TetrisRunnable(currentTetrimino);
		tetrisRunnable.addPropertyChangeListener(this);
		gameThread = new Thread(tetrisRunnable);
		gameThread.start();

		drowNextTetrimino(nextTetriminoCanvas, tetriminos.get(0));
		drowNextTetrimino(tetriminoAfterNextCanvas, tetriminos.get(1));
	}

	public void initView(final Stage stage) {
		stage.getScene().setOnKeyPressed(e -> {
			tetrisService.handleTetorimino(e, currentTetrimino, tetrisField.getColors());
			tetrisService.initCanvas(tetrisCanvas);
			draw(tetrisCanvas.getGraphicsContext2D(), tetrisField.getColors(), currentTetrimino);
		});

		stage.getScene().setOnKeyReleased(e -> {
			tetrisService.handleTetorimino(e, currentTetrimino, tetrisField.getColors());
			tetrisService.initCanvas(tetrisCanvas);
			draw(tetrisCanvas.getGraphicsContext2D(), tetrisField.getColors(), currentTetrimino);
		});
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// 底に着いたらテトリミノが落ちるのストップ
		if (tetrisService.isBottom(tetrisField.getColors(), currentTetrimino)) {
			tetrisField.fixTetrimino(currentTetrimino);
			rollbackTetrimino(tetrisCanvas.getGraphicsContext2D(), tetrisField.getColors(), currentTetrimino);
			currentTetrimino = updateTetriminos(tetriminos);
			drowNextTetrimino(nextTetriminoCanvas, tetriminos.get(0));
			drowNextTetrimino(tetriminoAfterNextCanvas, tetriminos.get(1));
			tetrisRunnable.setTetrimino(currentTetrimino);
		}

		if (tetrisService.isOverlap(tetrisField.getColors(), currentTetrimino)) {
			tetrisRunnable.stop();
			tetrisService.terminateAnimation(tetrisField.getColors());
			tetrisService.drawField(tetrisCanvas.getGraphicsContext2D(), tetrisField.getColors());
			tetrisCanvas.getGraphicsContext2D().setFill(Color.RED);
			tetrisCanvas.getGraphicsContext2D().fillText("GAME OVER", TetrisField.BLOCK_SIZE * 3,
					TetrisField.BLOCK_SIZE * 10);
			return;
		}

		tetrisService.initCanvas(tetrisCanvas);
		draw(tetrisCanvas.getGraphicsContext2D(), tetrisField.getColors(), currentTetrimino);
	}

	private void drowNextTetrimino(final Canvas canvas, final Tetrimino tetrimino) {
		tetrisService.initCanvas(canvas);
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

	private void rollbackTetrimino(final GraphicsContext gc, final Color[][] fieldColors, final Tetrimino tetrimino) {
		tetrimino.moveUp();
		draw(gc, fieldColors, tetrimino);
	}

	private void draw(final GraphicsContext gc, final Color[][] fieldColors, final Tetrimino tetorimino) {
		removeLines(gc, fieldColors);
		tetrisService.drawField(gc, fieldColors);
		tetrisService.drawFallingTetrimino(gc, tetorimino);
	}

	private void removeLines(final GraphicsContext gc, final Color[][] fieldColors) {
		List<Integer> clearLineIndexs = tetrisService.clearLines(fieldColors);
		if (clearLineIndexs.size() < 1) {
			return;
		}
		final int additionScore = calculateAdditionScore(clearLineIndexs.size());
		clearAnimation(gc, fieldColors, clearLineIndexs, additionScore);
		addScore(additionScore);
		clearLineCounts += clearLineIndexs.size();
		if (clearLineCounts >= 5) {
			addLevel();
			clearLineCounts = 0;
			levelUpAnimation(gc);
		}
		tetrisService.fillBlankLines(fieldColors, clearLineIndexs);
	}

	private void clearAnimation(final GraphicsContext gc, final Color[][] fieldColors, List<Integer> clearLineIndexs,
			final int additionScore) {
		tetrisService.clearLinesAnimation(fieldColors, clearLineIndexs);
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

		gc.setFill(Color.WHITE);
		gc.fillText(String.valueOf(additionScore), TetrisField.BLOCK_SIZE * 4,
				TetrisField.BLOCK_SIZE * clearLineIndexs.get(0));
		// アニメーションを流す時間
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	private int calculateAdditionScore(int clearLineNum) {
		return SCORE_BASIS_VALUE * Integer.parseInt(levelValue.getText()) * clearLineNum;
	}

	private void addScore(int additionScore) {
		final int oldScore = Integer.parseInt(scoreValue.getText());
		final int newScore = oldScore + additionScore;
		Platform.runLater(() -> {
			scoreValue.setText(Integer.toString(newScore));
		});
	}

	private void addLevel() {
		final int oldLevel = Integer.parseInt(levelValue.getText());
		final int newLevel = oldLevel + 1;
		Platform.runLater(() -> {
			levelValue.setText(Integer.toString(newLevel));
		});
	}

	private void levelUpAnimation(final GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillText("LEVEL UP!", tetrisCanvas.getWidth() / 2, tetrisCanvas.getHeight() / 2);
	}

//	private void fillBlankLines(final GraphicsContext gc, final Color[][] fieldColors, List<Integer> clearLineIndexs) {
//		tetrisService.fillBlankLines(fieldColors, clearLineIndexs);
//		for (int i = 0; i < TetrisField.HEIGHT; i++) {
//			for (int j = 0; j < fieldColors[i].length; j++) {
//				if (fieldColors[i][j] != null) {
//					gc.setFill(fieldColors[i][j]);
//					gc.fillRect(TetrisField.BLOCK_SIZE * j, TetrisField.BLOCK_SIZE * i, TetrisField.BLOCK_SIZE,
//							TetrisField.BLOCK_SIZE);
//					gc.setStroke(Color.BLACK);
//					gc.strokeRect(TetrisField.BLOCK_SIZE * j, TetrisField.BLOCK_SIZE * i, TetrisField.BLOCK_SIZE,
//							TetrisField.BLOCK_SIZE);
//				}
//			}
//		}
//	}

//	private void drawField(final GraphicsContext gc, final Color[][] fieldColors) {
//		for (int i = 0; i < TetrisField.HEIGHT; i++) {
//			for (int j = 0; j < fieldColors[i].length; j++) {
//				if (fieldColors[i][j] != null) {
//					gc.setFill(fieldColors[i][j]);
//					gc.fillRect(TetrisField.BLOCK_SIZE * j, TetrisField.BLOCK_SIZE * i, TetrisField.BLOCK_SIZE,
//							TetrisField.BLOCK_SIZE);
//					gc.setStroke(Color.BLACK);
//					gc.strokeRect(TetrisField.BLOCK_SIZE * j, TetrisField.BLOCK_SIZE * i, TetrisField.BLOCK_SIZE,
//							TetrisField.BLOCK_SIZE);
//				}
//			}
//		}
//	}

//	private void drawFallingTetrimino(final GraphicsContext gc, final Tetrimino tetrimino) {
//		final int x = tetrimino.getX();
//		final int y = tetrimino.getY();
//		final int r = tetrimino.getRotationPosition();
//		gc.setFill(tetrimino.getColor());
//		for (int i = 0; i < tetrimino.getMino()[r].length; i++) {
//			for (int j = 0; j < tetrimino.getMino()[0][i].length; j++) {
//				if (tetrimino.getMino()[r][i][j] != 0) {
//					gc.fillRect(TetrisField.BLOCK_SIZE * (j + x), TetrisField.BLOCK_SIZE * (i + y),
//							TetrisField.BLOCK_SIZE, TetrisField.BLOCK_SIZE);
//					gc.setStroke(Color.BLACK);
//					gc.strokeRect(TetrisField.BLOCK_SIZE * (j + x), TetrisField.BLOCK_SIZE * (i + y),
//							TetrisField.BLOCK_SIZE, TetrisField.BLOCK_SIZE);
//				}
//			}
//		}
//	}

	private Tetrimino updateTetriminos(final LinkedList<Tetrimino> tetriminos) {
		tetriminos.offer(TetriminoFactory.createRandomTetrimino());
		return tetriminos.poll();
	}

//	private void initCanvas(final Canvas canvas) {
//		final GraphicsContext gc = canvas.getGraphicsContext2D();
//		// Init
//		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//		// Set backgroundColor
//		gc.setFill(Color.BLACK);
//		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//		gc.setStroke(Color.WHITE);
//		gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
//	}
}
