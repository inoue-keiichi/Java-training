package ch03.ex08;

public class PassengerVehicle extends Vehicle implements Cloneable {
	private final int seatNum;
	private int sittingSeatNum;

	public PassengerVehicle(String owner, int seatNum, EnergySource energySource) {
		super(owner, energySource);
		this.seatNum = seatNum;
	}

	public PassengerVehicle(String owner, int seatNum) {
		super(owner);
		this.seatNum = seatNum;
	}

	public PassengerVehicle(int seatNum) {
		this(null, seatNum);
	}

	public PassengerVehicle clone() {
		PassengerVehicle passengerVehicle = (PassengerVehicle) super.clone();
		return passengerVehicle;
	}

	public final int getSeatNum() {
		return this.seatNum;
	}

	public final int getSittingSeatNum() {
		return this.sittingSeatNum;
	}

	public final void setSittingSeatNum(int sittingSeatNum) {
		this.sittingSeatNum = sittingSeatNum;
	}

	public static void main(String[] args) {
		PassengerVehicle family = new PassengerVehicle("Takeshi", 5, new Battery(100));
		family.setSittingSeatNum(4);

		PassengerVehicle clonedFamily = family.clone();
		clonedFamily.setOwner("Mike");
		clonedFamily.getEnergySource().setAmount(70);
		clonedFamily.setSittingSeatNum(5);
	
		System.out.println("Owner : " + family.getOwner());
		System.out.println("amount : " + family.getEnergySource().getAmount());
		System.out.println("the number of passengers is " + family.getSittingSeatNum());
		System.out.println("--------------------------------------------");
		System.out.println("Owner : " + clonedFamily.getOwner());
		System.out.println("amount : " + clonedFamily.getEnergySource().getAmount());
		System.out.println("the number of passengers is " + clonedFamily.getSittingSeatNum());
	}
}
