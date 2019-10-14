package ch03.ex12;

public abstract class SortHarness {
	private final Object[] values;
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
		Comparable obj1 = (Comparable) values[i];
		Comparable obj2 = (Comparable) values[j];

		if (obj1.compareTo(obj2) == 0) {
			return 0;
		} else {
			return (obj1.compareTo(obj2) < 0 ? -1 : 1);
		}
	}

	protected final void swap(int i, int j) {
		curMetrics.swapCnt++;
		Object tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}

	protected abstract void doSort();
}
