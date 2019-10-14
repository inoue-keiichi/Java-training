package ch04.ex02;

public abstract class SortHarness implements Comparable<Object> {
	private final Object[] values;
	protected Object compareVal;
	private final SortMetrics curMetrics = new SortMetrics();

	SortHarness(Object[] values) {
		this.values = values;
	}

//	全ソートをするために呼び出される
	public final SortMetrics sort() {
		curMetrics.init();
		doSort();
		return getMetrics();
	}

	public final SortMetrics getMetrics() {
		return curMetrics.clone();
	}

	protected final int getDataLength() {
		return values.length;
	}

	protected final Object probe(int i) {
		curMetrics.probeCnt++;
		return values[i];
	}

	protected final int compare(int i, int j) {
		curMetrics.compareCnt++;
		compareVal = values[i];
		Object obj = values[j];
		return compareTo(obj);
	}

	protected final void swap(int i, int j) {
		curMetrics.swapCnt++;
		Object tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}

	protected abstract void doSort();

	public abstract int compareTo(Object obj);
}
