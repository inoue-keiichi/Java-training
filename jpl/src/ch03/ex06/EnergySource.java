package ch03.ex06;

public abstract class EnergySource {
	private int amount;

	EnergySource(int amount) {
		this.amount = amount;
	}

	abstract public boolean empty();

	public final int getAmount() {
		return this.amount;
	}

	public final void setAmount(int amount) {
		this.amount = amount;
	}

}
