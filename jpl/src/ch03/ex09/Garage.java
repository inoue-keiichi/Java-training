package ch03.ex09;

import ch03.ex08.Vehicle;

public class Garage implements Cloneable {
	private Vehicle[] vehicles;

	public Garage(Vehicle[] vehicles) {
		this.vehicles = vehicles;
	}

	public static void main(String[] args) {
		final Vehicle car1 = new Vehicle("Akira");
		final Vehicle car2 = new Vehicle("Bob");
		final Vehicle car3 = new Vehicle("Cancey");
		final Vehicle car4 = new Vehicle("Daigo");
		final Vehicle[] vehicles = { car1, car2, car3, car4 };
		final Garage garage = new Garage(vehicles);

		Garage clonedGarage = garage.clone();
		clonedGarage.getVehicles()[0].setOwner("アキラ");
		clonedGarage.getVehicles()[1].setOwner("ボブ");
		clonedGarage.getVehicles()[2].setOwner("キャシー");
		clonedGarage.getVehicles()[3].setOwner("ダイゴ");

		for (Vehicle car : garage.vehicles) {
			System.out.println(car.getOwner());
		}
		System.out.println("------------------------");
		for (Vehicle car : clonedGarage.vehicles) {
			System.out.println(car.getOwner());
		}

	}

	public Garage clone() {
		try {
			Garage garage = (Garage) super.clone();
			garage.vehicles = this.vehicles.clone();
			for (int i = 0; i < garage.vehicles.length; i++) {
				garage.vehicles[i] = this.vehicles[i].clone();
			}
			return garage;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	public final Vehicle[] getVehicles() {
		return vehicles;
	}

	public final void setVehicles(Vehicle[] vehicles) {
		this.vehicles = vehicles;
	}
}
