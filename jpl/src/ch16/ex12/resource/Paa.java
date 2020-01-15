package ch16.ex12.resource;

import static ch16.ex12.Hand.Paa;

import java.util.Random;

import ch16.ex12.Hand;

public class Paa {
	public Hand book = strategy();

	protected Hand strategy() {
		return Hand.get(Paa.getId());
	}
}
