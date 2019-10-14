package ch03.ex08;

public class GasTank extends EnergySource implements Cloneable {

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
