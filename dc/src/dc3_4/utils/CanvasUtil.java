package dc3_4.utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasUtil {
	public static void initGc(final GraphicsContext gc, double width, double height) {
		gc.clearRect(0, 0, width, height);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, width, height);
	}

	public static void initGc(final GraphicsContext gc, final Color color, double width, double height) {
		gc.clearRect(0, 0, width, height);
		gc.setFill(color);
		gc.fillRect(0, 0, width, height);
	}
}
