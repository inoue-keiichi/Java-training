package ch16.ex12.resource;

import static ch16.ex12.Hand.Guu;

import java.util.Random;

import ch16.ex12.Hand;

public class Guu {
	public Hand book = strategy();
	
	protected Hand strategy() {
		return Hand.get(Guu.getId());
	}
}
