package ch03.ex08;

enum VehicleAngle {
	TURN_LEFT, TURN_RIGHT
}

public class Vehicle implements Cloneable {
	private static int nextid = 0;

	private final int id;
	private String owner;
	private int angle;
	private int speed;
	private EnergySource energySource;

	public static void main(String[] args) {
		final String owner = "Takashi";
		final EnergySource energySource = new Battery(100);
		final Vehicle car = new Vehicle(owner, energySource);

		Vehicle clonedCar = car.clone();
		clonedCar.setOwner("Mike");
		clonedCar.getEnergySource().setAmount(80);

		System.out.println("Owner : " + car.getOwner());
		System.out.println("amount : " + car.getEnergySource().getAmount());
		System.out.println("----------------------");
		System.out.println("Owner : " + clonedCar.getOwner());
		System.out.println("amount : " + clonedCar.getEnergySource().getAmount());

	}

	public Vehicle(String owner, EnergySource energy) {
		this(owner);
		this.energySource = energy;
	}

	public Vehicle(String owner) {
		this.owner = owner;
		this.id = nextid;
		this.energySource = new GasTank(0);
		nextid++;
	}

	public Vehicle clone() {
		Vehicle vehicle;
		try {
			vehicle = (Vehicle) super.clone();
			vehicle.energySource = (EnergySource) this.energySource.clone();
			return vehicle;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}

	}

	public void start() {
		if (energySource.empty()) {
			System.out.println("Your car dosen't have energy.");
			return;
		}

		System.out.println("Let's go!");

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

	public final EnergySource getEnergySource() {
		return energySource;
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
