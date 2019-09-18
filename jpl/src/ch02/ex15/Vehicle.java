package src.ch02.ex15;

public class Vehicle {
	private static int nextid = 0;

	private final int id;
	private String owner;
	private int angle;
	private int speed;
	
	public static void main(String[] args) {		
		final String owner = args.toString(); 
		final Vehicle vitz = new Vehicle(owner);

		System.out.println("owner : " + vitz.owner);
	}

	public Vehicle(String owner) {
		this.owner = owner;
		this.id = nextid;
		nextid++;
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
