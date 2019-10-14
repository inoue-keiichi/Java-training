package ch02.ex03;

public class Vehicle {
	public static int nextid = 0;

	public int id;
	public String owner;
	public int angle;
	public int speed;
	
	public Vehicle() {
		this.id = nextid;
		nextid++;
	}
}
