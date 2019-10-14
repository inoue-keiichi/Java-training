package ch04.ex01;

public class Battery extends EnergySourceImpl implements EnergySource {
	private int amount;

	Battery(int amount) {
		super(amount);
	}
}
