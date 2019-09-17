package src.ch02.ex09;

public class Vehicle {
	public static int nextid = 0;

	public int id;
	public String owner;
	public int angle;

	public Vehicle(String owner) {
		this.owner = owner;
		this.id = nextid;
		nextid++;
	}
	
	public static int getMaxId() {
		int maxId = nextid-1;
		return maxId;
	}
}
