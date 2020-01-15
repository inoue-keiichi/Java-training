package ch16.ex11;

import java.util.Random;

public class RandomPlayer extends Player {
	private final Random r = new Random();

	protected Hand strategy() {
		return Hand.get(r.nextInt(3));
	}
}
