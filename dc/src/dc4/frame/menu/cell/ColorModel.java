package dc4.frame.menu.cell;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ColorModel {
	private final StringProperty colorName = new SimpleStringProperty();

	public ColorModel(final String colorName) {
		this.colorName.set(colorName);
	}

	public StringProperty getColorNameProperty() {
		return colorName;
	}

	@Override
	public String toString() {
		return colorName.get();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ColorModel)) {
			return false;
		}
		final ColorModel colorModel = (ColorModel) obj;
		if (this.colorName.get() != colorModel.getColorNameProperty().get()) {
			return false;
		}
		return true;
	}
}
