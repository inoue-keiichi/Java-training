package src.ch02.ex10;

public class Vehicle {
	public static int nextid = 0;

	public int id;
	public String owner;
	public int angle;
	public int speed;

	public Vehicle(String owner) {
		this.owner = owner;
		this.id = nextid;
		nextid++;
	}
	
	public static int getMaxId() {
		int maxId = nextid-1;
		return maxId;
	}
	
	public String toString() {
		String str = id + "(" + owner + ")";
		return str;
	}
}