package ch02.ex07;

public class Vehicle {
	public static void main(String[] args) {
		final Vehicle toyota = new Vehicle("Tom");
		final Vehicle daihatsu = new Vehicle("Mike");
		final Vehicle nissan = new Vehicle("Mai");

		System.out.println("Id : " + toyota.id);
		System.out.println("Angle : " + toyota.angle);
		System.out.println("Owner : " + toyota.owner);
		System.out.println("Id : " + daihatsu.id);
		System.out.println("Angle : " + daihatsu.angle);
		System.out.println("Owner : " + daihatsu.owner);
		System.out.println("Id : " + nissan.id);
		System.out.println("Angle : " + nissan.angle);
		System.out.println("Owner : " + nissan.owner);
	}
	
	public Vehicle () {
		
	}
	
	public Vehicle(String owner) {
		this.owner = owner;
		this.id = nextid;
		nextid++;
	}

	public static int nextid = 0;

	public int id;
	public String owner;
	public int angle;
	public int speed;
}
