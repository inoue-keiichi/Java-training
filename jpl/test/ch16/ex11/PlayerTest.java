package ch16.ex11;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static ch16.ex11.Game.GameResult.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {
	@Test
	void test() {
		Player player = new RandomPlayer();
		Game game = new Game();
		player.play(game);
		assertEquals(1000, player.result.get(Win) + player.result.get(Lose) + player.result.get(Draw));
	}
}