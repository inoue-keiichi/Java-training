package ch03.ex06;

public class Battery extends EnergySource {
	public Battery(int amount){
		super(amount);
	}
	
	@Override
	public boolean empty() {
		if(getAmount() > 0) {
			return false;
		}
		return true;
	}

}
