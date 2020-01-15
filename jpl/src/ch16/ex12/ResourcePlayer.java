package ch16.ex12;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import ch16.ex12.Player;
import ch16.ex12.PlayerLoader;
import ch16.ex12.Game.GameResult;
import static ch16.ex12.Game.GameResult.*;
import static ch16.ex12.Hand.*;

public class ResourcePlayer extends Player {
	private static final PlayerLoader loader = new PlayerLoader();
	final String book = "/Users/user/git/Java-training/jpl/src/ch16/ex12/strategy.properties";
	private Hand hand = Hand.valueOf((String) getProperties(book).get("hand"));

	private Properties getProperties(String book) {
		InputStream in;
		final Properties props = new Properties();
		;
		final ClassLoader loader = this.getClass().getClassLoader();
		if (loader != null)
			in = loader.getResourceAsStream(book);
		else
			in = ClassLoader.getSystemResourceAsStream(book);
		try {
			props.load(in);
			in.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return props;
	}

	@Override
	protected Hand strategy() {
		return hand;
	}
}
