package dc3_2.menu.cell;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FontModel {
	private final StringProperty fontFamily = new SimpleStringProperty();

	public FontModel(final String fontFamily) {
		this.fontFamily.set(fontFamily);
	}

	public StringProperty getFontFamilyProperty() {
		return fontFamily;
	}

	@Override
	public String toString() {
		return fontFamily.get();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FontModel)) {
			return false;
		}
		final FontModel fontModel = (FontModel) obj;
		if (this.fontFamily.get() != fontModel.getFontFamilyProperty().get()) {
			return false;
		}
		return true;
	}
}
