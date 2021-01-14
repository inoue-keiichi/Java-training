package ch09.ex10;

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

	/**
	 * x,y,labelの順に比較し、以下のルールで値を返します。
	 * other.xよりthis.xの方が大きい場合は1を、小さい場合は-1を返します。
	 * other.yよりthis.yの方が大きい場合は1を、小さい場合は-1を返します。
	 * this.labelとother.labelが異なる場合は、1を返します。
	 * x, y, labelが全て等しい場合は0を返します。
	 *
	 * @param other 比較対象
	 * @return 比較結果
	 */
	public int compareTo(LabeledPoint other) {
		int diffX = Integer.compare(this.x, other.x);
		if (diffX != 0) {
			return diffX;
		}
		int diffY = Integer.compare(this.x, other.y);
		if (diffY != 0) {
			return diffY;
		}
		return Objects.equals(this.label, other.label) ? 0 : 1;
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
