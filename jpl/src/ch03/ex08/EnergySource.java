package ch03.ex08;

public abstract class EnergySource implements Cloneable {
	private int amount;

	EnergySource(int amount) {
		this.amount = amount;
	}

	abstract public boolean empty();
	
	public EnergySource clone() {
		EnergySource energySource;
		try {
			energySource = (EnergySource)super.clone();
			return energySource;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}	
	}

	public final int getAmount() {
		return this.amount;
	}

	public final void setAmount(int amount) {
		this.amount = amount;
	}
}
