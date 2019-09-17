package src.ch02.ex03;

public class Vehicle {
	public static int nextid = 0;

	private int id;
	public String owner;
	public int angle;
	
	public Vehicle() {
		this.id = nextid;
		nextid++;
	}
	
	public int getId() {
		return id;
	}
}
