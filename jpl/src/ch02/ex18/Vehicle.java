package ch02.ex18;

enum VehicleAngle {
	TURN_LEFT, TURN_RIGHT
}

public class Vehicle {
	private static int nextid = 0;

	private final int id;
	private String owner;
	private int angle;
	private int speed;

	public static void main(String[] args) {
		String owner;
		Vehicle car;
		for (int i = 0; i < args.length; i++) {
			owner = args[i];
			car = new Vehicle(owner);
			System.out.println("Owner : " + car.getOwner());
		}
	}

	public Vehicle(String owner) {
		this.owner = owner;
		this.id = nextid;
		nextid++;
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
