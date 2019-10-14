package ch04.ex01;

public class EnergySourceImpl implements EnergySource {
	private int amount;

	EnergySourceImpl(int amount) {
		this.amount = amount;
	}

	@Override
	public boolean empty() {
		if (amount > 0) {
			return false;
		}
		return true;
	}

}
