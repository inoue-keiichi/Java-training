package ch03.ex07;

import java.util.Objects;

public class ColorAttr extends Attr {

	public ColorAttr(String name, Object value) {
		super(name, value);
	}

	public ColorAttr(String name) {
		this(name, "transparent");
	}

	@Override
	public boolean equals(Object obj) {
		if (Objects.isNull(obj)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ColorAttr)) {
			return false;
		}
		ColorAttr colorAttr = (ColorAttr) obj;
		if (this.getName() != colorAttr.getName()) {
			return false;
		}
		if (this.getValue() != colorAttr.getValue()) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = 17;

		result *= 31;
		result += getName().hashCode();

		result *= 31;
		result += getValue().hashCode();

		return result;
	}
}
