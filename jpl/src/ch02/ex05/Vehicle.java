package src.ch02.ex05;

public class Vehicle {
	public static void main(String arg[]) {
		final Vehicle toyota = new Vehicle();
		toyota.owner = "Tom";
		final Vehicle daihatsu = new Vehicle();
		toyota.owner = "Mike";
		final Vehicle nissan = new Vehicle();
		toyota.owner = "Mai";

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

	public static int nextid = 0;

	public int id;
	public String owner;
	public int angle;
}
