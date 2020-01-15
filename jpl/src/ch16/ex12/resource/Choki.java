package ch16.ex12.resource;

import static ch16.ex12.Hand.Choki;

import java.util.Random;

import ch16.ex12.Hand;

public class Choki {
	public Hand book = strategy();
	
	protected Hand strategy() {
		return Hand.get(Choki.getId());
	}
}
