package ch16.ex11;

import static ch16.ex11.Game.GameResult.*;
import static ch16.ex11.Game.GameResult.Draw;
import static ch16.ex11.Game.GameResult.Lose;
import static ch16.ex11.Game.GameResult.Win;

import java.util.HashMap;
import java.util.Map;

import ch16.ex11.Game.GameResult;
import ch16.ex11.Game;
import ch16.ex11.Hand;

public abstract class Player {
	private final int MATCHES_NUM = 1000;
	protected Hand hand;
	protected Map<GameResult, Integer> result = new HashMap<GameResult, Integer>() {
		{
			put(Win, 0);
			put(Lose, 0);
			put(Draw, 0);
		}
	};

	protected abstract Hand strategy();

	public final void play(Game game) {
		for (int i = 0; i < MATCHES_NUM; i++) {
			hand = strategy();
			switch (game.fight(hand)) {
			case Win:
				result.get(Win);
				result.put(Win, result.get(Win) + 1);
				break;
			case Lose:
				result.get(Lose);
				result.put(Lose, result.get(Lose) + 1);
				break;
			case Draw:
				result.get(Draw);
				result.put(Draw, result.get(Draw) + 1);
				break;
			default:
			}
		}
		game.result = result;
	}
}
