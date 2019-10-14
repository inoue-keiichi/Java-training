package ch03.ex06;

public class GasTank extends EnergySource {

	public GasTank(int amount) {
		super(amount);
	}

	@Override
	public boolean empty() {
		if (getAmount() > 0) {
			return false;
		}
		return true;
	}

}
