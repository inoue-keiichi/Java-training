package ch04.ex02;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// プロパティメソッドを呼び出すまでは、普通のフィールドを利用する。
// プロパティメソッドを呼び出した時にインスタンス生成する。普通のフィールドの値をプロパティに設定する。
public class Greeting {
	private StringProperty textProperty = null;
	private String text = null;

	public StringProperty textProperty() {
		if (textProperty == null) {
			textProperty = new SimpleStringProperty();
			// 外部からは、setText(text)の呼び出し時にプロパティに値を設定していたかのように見せたい
			textProperty.setValue(text);
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
		if (textProperty == null) {
			this.text = text;
		}
		this.textProperty.set(text);
	}
}
