package ch04.ex02;

final class SortMetrics implements Cloneable {
	public long probeCnt;
	public long compareCnt;
	public long swapCnt;

	public void init() {
		probeCnt = swapCnt = compareCnt = 0;
	}

	public String toString() {
		return probeCnt + "probes " + compareCnt + " compares" + swapCnt + " swaps";
	}

	// このクラスは、cloneをサポートしている
	public SortMetrics clone() {
		try {
			return (SortMetrics) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
}
