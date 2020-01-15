
package ch16.ex11;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import ch16.ex11.Game.GameResult;

import static ch16.ex11.Hand.*;

public class PaaPlayer extends Player {
	private final Random r = new Random();

	protected Hand strategy() {
		return Hand.get(Paa.getId());
	}
}
