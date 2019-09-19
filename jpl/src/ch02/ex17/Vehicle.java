package src.ch02.ex17;

public class Vehicle {
	public enum VehicleAngle {
		TURN_LEFT, TURN_RIGHT
	}
	private static int nextid = 0;

	private final int id;
	private String owner;
	private int angle;
	private int speed;

	public Vehicle(String owner) {
		this.owner = owner;
		this.id = nextid;
		nextid++;
	}
	
	public Vehicle() {
		this.id = nextid;
		nextid++;
	}

	public void turn(int angle) {
		this.angle = this.angle + angle;
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

	public static int getMaxId() {
		int maxId = nextid - 1;
		return maxId;
	}

	public String toString() {
		String str = id + "(" + owner + ")";
		return str;
	}

	public int getId() {
		return id;
	}

	public String getOwner() {
		return owner;
	}

	public int getAngle() {
		return angle;
	}

	public int getSpeed() {
		return speed;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
