package ch03.ex06;

enum VehicleAngle {
	TURN_LEFT, TURN_RIGHT
}

public class Vehicle {
	private static int nextid = 0;

	private final int id;
	private String owner;
	private int angle;
	private int speed;
	private EnergySource energy;

	public static void main(String[] args) {
		String owner;
		Vehicle car;
		EnergySource energy;
		owner = "Takashi";
		energy = new Battery(0);
		car = new Vehicle(owner, energy);
		System.out.println("Owner : " + car.getOwner());
		car.start();
		
		owner = "Ichiro";
		energy = new GasTank(100);
		car = new Vehicle(owner, energy);
		System.out.println("Owner : " + car.getOwner());
		car.start();
	}

	public Vehicle(String owner, EnergySource energy) {
		this(owner);
		this.energy = energy;
	}

	public Vehicle(String owner) {
		this.owner = owner;
		this.id = nextid;
		nextid++;
	}

	public void start() {
		if (energy.empty()) {
			System.out.println("Your car dosen't have energy.");
			return;
		}

		System.out.println("Let's go!");

	}

	public void turn(int angle) {
		this.angle = this.angle - angle;
	}

	public void turn(VehicleAngle angle) {
		if (angle == VehicleAngle.TURN_LEFT) {
			this.angle = this.angle + 90;
		} else if (angle == VehicleAngle.TURN_RIGHT) {
			this.angle = this.angle - 90;
		}
	}

	public void changeSpeed(int speed) {
		this.speed = speed;
	}

	public void stop() {
		speed = 0;
	}

	public static final int getMaxId() {
		int maxId = nextid - 1;
		return maxId;
	}

	public String toString() {
		String str = id + "(" + owner + ")";
		return str;
	}

	public final int getId() {
		return id;
	}

	public final String getOwner() {
		return owner;
	}

	public final int getAngle() {
		return angle;
	}

	public final int getSpeed() {
		return speed;
	}

	public final void setOwner(String owner) {
		this.owner = owner;
	}

	public final void setAngle(int angle) {
		this.angle = angle;
	}

	public final void setSpeed(int speed) {
		this.speed = speed;
	}
}
