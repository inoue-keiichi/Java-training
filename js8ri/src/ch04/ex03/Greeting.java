package ch04.ex03;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// プロパティのデフォルト値のフィールドを用意する。
// プロパティメソッドを呼び出すまでは、プロパティオブジェクトはnull。getxxx()を呼び出されたらデフォルト値のフィールドを返す。
// プロパティメソッドを呼び出すか、デフォルトでない値に設定された時にインスタンス生成する。
public class Greeting {
	private final String text = "";
	private StringProperty textProperty = null;

	public StringProperty textProperty() {
		if (textProperty == null) {
			textProperty = new SimpleStringProperty();
		}
		return textProperty;
	}

	private String getText() {
		if (textProperty == null) {
			return text;
		}
		return textProperty.get();
	}

	private void setText(final String text) {
		if (this.text.equals(text)) {
			return;
		} else if (textProperty == null) {
			textProperty = new SimpleStringProperty();
		}
		this.textProperty.set(text);
	}
}
