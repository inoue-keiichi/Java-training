package dc3_4.frame.menu.cell;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ColorCell extends ListCell<ColorModel> {
	private static final int HBOX_SPACING = 3;
	private static final int CANVAS_WIDTH = 10;
	private static final int CANVAS_HEIGHT = 10;
	private static final double FONT_SIZE = 12.0;
	private static final String FONT_NAME = "System";

	private HBox cellContainer;
	private Canvas colorCanvas;
	private Text colorName;

	public ColorCell() {
		initComponent();
		initStyle();
	}

	private void initStyle() {
		colorName.setFont(new Font(FONT_NAME, FONT_SIZE));
	}

	private void initComponent() {
		cellContainer = new HBox(HBOX_SPACING);
		colorName = new Text();
		colorCanvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		cellContainer.setAlignment(Pos.CENTER_LEFT);
		cellContainer.getChildren().add(colorCanvas);
		cellContainer.getChildren().add(colorName);
	}

	@Override
	protected void updateItem(final ColorModel colorModel, final boolean empty) {
		super.updateItem(colorModel, empty);
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			draw(colorModel.getColorNameProperty().get());
			colorName.setText(colorModel.getColorNameProperty().get());
			setGraphic(cellContainer);
		}
	}

	private void draw(final String colorName) {
		final GraphicsContext gc = colorCanvas.getGraphicsContext2D();
		gc.setFill(Color.valueOf(colorName));
		gc.fillRect(0, 0, colorCanvas.getWidth(), colorCanvas.getHeight());
		gc.setStroke(Color.BLACK);
		gc.strokeRect(0, 0, colorCanvas.getWidth(), colorCanvas.getHeight());
	}
}
