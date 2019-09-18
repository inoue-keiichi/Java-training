package src.ch02.ex13;

public class Vehicle {
	private static int nextid = 0;
	
	//idは同じものがあってはならないので不変であるべき。
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
		
	//nextIdは次に作るオブジェクトのidに入れる値であり他のidと被らない値にしたいので、アクセッサーメソッドは持つべきでない。
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
	
	public static int getMaxId() {
		int maxId = nextid-1;
		return maxId;
	}
	
	public String toString() {
		String str = id + "(" + owner + ")";
		return str;
	}
}
