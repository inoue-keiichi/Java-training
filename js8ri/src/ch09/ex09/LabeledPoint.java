package ch09.ex09;

import java.util.Objects;

public class LabeledPoint {
	private String label;
	private int x;
	private int y;

	public LabeledPoint(String label, int x, int y) {
		this.label = label;
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LabeledPoint)) {
			return false;
		}
		LabeledPoint target = (LabeledPoint) other;
		return Objects.equals(this.label, target.label) && Objects.equals(this.x, target.x)
				&& Objects.equals(this.y, target.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.label, this.x, this.y);
	}
}
