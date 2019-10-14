package ch03.ex01;

import ch02.ex18.Vehicle;

public class PassengerVehicle extends Vehicle {
	private final int seatNum;
	private int sittingSeatNum;

	public PassengerVehicle(String owner, int seatNum) {
		super(owner);
		this.seatNum = seatNum;
	}

	public PassengerVehicle(int seatNum) {
		this(null, seatNum);
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
		PassengerVehicle vitz = new PassengerVehicle(5);
		PassengerVehicle noa = new PassengerVehicle(8);
		System.out.println("the number of passengers is " + vitz.getSeatNum());
		System.out.println("the number of passengers is " +noa.getSeatNum());
	}
}
