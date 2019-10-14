package ch03.ex08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PassengerVehicleTest{
	
	//クローンが作成されていることをテスト
	@Test
	void createCloneTest() {
		PassengerVehicle family = new PassengerVehicle("Takeshi", 5, new Battery(100));
		family.setSittingSeatNum(4);
		PassengerVehicle clonedFamily = family.clone();
		
		assertEquals(family.getOwner(), clonedFamily.getOwner());
		assertEquals(family.getSeatNum(), clonedFamily.getSeatNum());
		assertEquals(family.getSittingSeatNum(), clonedFamily.getSittingSeatNum());
		assertEquals(family.getEnergySource().getAmount(), clonedFamily.getEnergySource().getAmount());
	}
	
	//クローンを変更してもクローン元に影響がないことをテスト
	@Test
	void CloneNotInfruenserTest() {
		PassengerVehicle family = new PassengerVehicle("Takeshi", 5, new Battery(100));
		family.setSittingSeatNum(4);
		
		PassengerVehicle clonedFamily = family.clone();
		clonedFamily.setOwner("Mike");
		clonedFamily.getEnergySource().setAmount(70);
		clonedFamily.setSittingSeatNum(5);
		
		assertEquals("Takeshi",family.getOwner());
		assertEquals(4, family.getSittingSeatNum());
		assertEquals(100, family.getEnergySource().getAmount());
	}
	
}
