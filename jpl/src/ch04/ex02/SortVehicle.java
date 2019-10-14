package ch04.ex02;

import ch03.ex08.Vehicle;

public class SortVehicle extends SortHarness {

	SortVehicle(Vehicle[] values) {
		super(values);
	}

	@Override
	protected void doSort() {
		for (int i = 0; i < getDataLength(); i++) {
			for (int j = i + 1; j < getDataLength(); j++) {
				if (compare(i, j) > 0) {
					swap(i, j);
				}
			}
		}
	}

	@Override
	public int compareTo(Object vehicle) {
		Vehicle vehicle1 = (Vehicle) compareVal;
		Vehicle vehicle2 = (Vehicle) vehicle;
		return vehicle1.getId() - vehicle2.getId();
	}
}
