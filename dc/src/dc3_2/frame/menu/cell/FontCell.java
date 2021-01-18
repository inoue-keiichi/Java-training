package dc3_2.frame.menu.cell;

import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FontCell extends ListCell<FontModel> {
	private static final double FONT_SIZE = 12.0;
	private static final String FONT_NAME = "System";

	private VBox cellContainer;
	private Text fontFamily;

	public FontCell() {
		initComponent();
		initStyle();
	}

	private void initStyle() {
		fontFamily.setFont(new Font(FONT_NAME, FONT_SIZE));
	}

	private void initComponent() {
		cellContainer = new VBox();
		fontFamily = new Text();
		cellContainer.getChildren().add(fontFamily);
	}

	@Override
	protected void updateItem(final FontModel fontModel, final boolean empty) {
		super.updateItem(fontModel, empty);
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			fontFamily.setFont(new Font(fontModel.getFontFamilyProperty().get(), FONT_SIZE));
			fontFamily.setText(fontModel.getFontFamilyProperty().get());
			setGraphic(cellContainer);
		}
	}
}
