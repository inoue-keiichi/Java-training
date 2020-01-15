package ch16.ex11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import ch16.ex11.Game;
import ch16.ex11.Hand;
import ch16.ex11.Player;
import ch16.ex11.PlayerLoader;
import static ch16.ex11.Game.GameResult.*;

public class Game {
	public static void main(String[] args) {
		String name;
		while ((name = getNextPlayer()) != null) {
			try {
				PlayerLoader loader = new PlayerLoader();
				Class<? extends Player> classOf = loader.loadClass(name).asSubclass(Player.class);
				Player player = classOf.newInstance();
				Game game = new Game();
				player.play(game);
				game.reportScore(name);
			} catch (Exception e) {
				reportException(name, e);
			}
		}
	}

	public Map<GameResult, Integer> result;
	private final Map<String, List<Map<GameResult, Integer>>> scoreRecode = new HashMap<>();

	private static Queue<String> players = new LinkedList<String>() {
		{
			add("/Users/user/git/Java-training/jpl/bin/ch16/ex11/RandomPlayer");
			add("/Users/user/git/Java-training/jpl/bin/ch16/ex11/RandomPlayer");
			add("/Users/user/git/Java-training/jpl/bin/ch16/ex11/RandomPlayer");
			add("/Users/user/git/Java-training/jpl/bin/ch16/ex11/PaaPlayer");
			add("/Users/user/git/Java-training/jpl/bin/ch16/ex11/PaaPlayer");
			add("/Users/user/git/Java-training/jpl/bin/ch16/ex11/PaaPlayer");
		}
	};

	private static String getNextPlayer() {
		return players.poll();
	}

	private void reportScore(String name) {
		System.out.println("name: " + name);
		System.out.println("Win: " + result.get(Win));
		System.out.println("Lose: " + result.get(Lose));
		System.out.println("Draw: " + result.get(Draw));
		if (!scoreRecode.containsKey(name)) {
			scoreRecode.put(name, new ArrayList<Map<GameResult, Integer>>());
		}
		scoreRecode.get(name).add(result);
	}

	public List<Map<GameResult, Integer>> getPlayerRecode(final String name) {
		return scoreRecode.get(name);
	}

	private static void reportException(String name, Exception e) {
		System.out.println(name + ": " + e.getMessage());
	}

	public enum GameResult {
		Win, Draw, Lose
	}

	public GameResult fight(Hand hand) {
		Random r = new Random();
		Hand cpuHand = Hand.get(r.nextInt(4) % 3); // グーを出す傾向にある
		if (cpuHand == hand) {
			return GameResult.Draw;
		} else if ((hand.getId() + 1) % 3 == cpuHand.getId()) {
			return GameResult.Win;
		}
		return GameResult.Lose;
	}
}