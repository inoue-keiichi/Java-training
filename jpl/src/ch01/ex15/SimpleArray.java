package src.ch01.ex15;

import java.util.Objects;

public class SimpleArray implements AddDelete {
	private String[] names;
	private Object[] values;

	@Override
	public Object find(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(name)) {
				return values[i];
			}
		}
		return null;
	}

	/**
	 * SimpleArrayオブジェクトのnamesフィールドと valuesフィールドの最後に引数の値を追加します。
	 * どちらか片方のみを追加することはできません。
	 *
	 * @param name  名前
	 * @param value 値
	 */
	public void add(String name, Object value) {
		int length;
		if (Objects.isNull(this.names)) {
			length = 1;
		} else {
			length = this.names.length + 1;
		}
		String[] names = new String[length];
		Object[] values = new Object[length];

		if (length == 1) {
			names[0] = name;
			values[0] = value;
			this.names = names;
			this.values = values;
			return;
		}

		for (int i = 0; i < length - 1; i++) {
			names[i] = this.names[i];
			values[i] = this.values[i];
		}
		names[length - 1] = name;
		values[length - 1] = value;
		this.names = names;
		this.values = values;
	}

	/**
	 * SimpleArrayオブジェクトの全てのフィールド値の配列の 最後の要素を除去します。
	 */
	public void remove() {
		int length = this.names.length - 1;
		String[] names = new String[length];
		Object[] values = new Object[length];
		for (int i = 0; i < length; i++) {
			names[i] = this.names[i];
			values[i] = this.values[i];
		}
		this.names = names;
		this.values = values;
	}
	
	public String[] getNames() {
		return this.names;
	}
	
	public Object[] getValues() {
		return this.values;
	}
}
