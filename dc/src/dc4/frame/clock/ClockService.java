package dc4.frame.clock;

import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;
import java.util.prefs.Preferences;

import dc4.frame.ScreenMode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ClockService {
	private static final ClockService clockService = new ClockService();

	private enum MenuKey {
		FONT_STYLE("fontStyle"), FONT_SIZE("fontSize"), FONT_COLOR("fontColor"), BACKGROUND_COLOR(
				"backgroundColor"), CLOCK_TYPE("clockType");

		private String name;

		MenuKey(final String name) {
			this.name = name;
		}
	}

	public static ClockService getInstance() {
		return clockService;
	}

	private static final String DEFAULT_TIMER_TEXT = "00:00:00";

	private String backgroundColorName;
	private String fontColorName;
	private int fontSize;
	private Font font;
	private ClockType clockType;

	private int hour;
	private int minute;
	private int second;

	private long updateSpeed;

	private String music;
	private String customMusic;
	private int alarmHour;
	private int alarmMinute;

	private final Preferences prefs;

	private ClockService() {
		prefs = Preferences.userNodeForPackage(this.getClass());
		updateSpeed = 1000;
		try {
			fontColorName = load(MenuKey.FONT_COLOR);
			backgroundColorName = load(MenuKey.BACKGROUND_COLOR);
			font = loadFont();
			clockType = loadClockType();
			music = "Sample1";
			alarmHour = 0;
			alarmMinute = 0;

		} catch (final IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public String load(final MenuKey key) throws IOException {
		switch (key) {
		case FONT_STYLE:
			return prefs.get(key.toString(), "Dialog");

		case FONT_SIZE:
			return prefs.get(key.toString(), "60");

		case FONT_COLOR:
			return prefs.get(key.toString(), "green");

		case BACKGROUND_COLOR:
			return prefs.get(key.toString(), "black");
		case CLOCK_TYPE:
			return prefs.get(key.toString(), ClockType.DEGITAL.toString());
		default:
			throw new IOException();
		}
	}

	private Font loadFont() throws NumberFormatException, IOException {
		final double fontSize = Double.valueOf(this.load(MenuKey.FONT_SIZE));
		final String fontFamily = this.load(MenuKey.FONT_STYLE);
		return Font.font(fontFamily, fontSize);
	}

	private ClockType loadClockType() throws NumberFormatException, IOException {
		final String clockTypeName = this.load(MenuKey.CLOCK_TYPE);
		return ClockType.get(clockTypeName);
	}

	public void saveFontStyle(String value) {
		prefs.put(MenuKey.FONT_STYLE.toString(), value);
	}

	public void saveFontSize(int value) {
		prefs.putInt(MenuKey.FONT_SIZE.toString(), value);
	}

	public void saveFontColor(String value) {
		prefs.put(MenuKey.FONT_COLOR.toString(), value);
	}

	public void saveBackgroundColor(String value) {
		prefs.put(MenuKey.BACKGROUND_COLOR.toString(), value);
	}

	public void save(final MenuKey key, final String value) {
		prefs.put(key.toString(), value);
	}

	public String getTime() {
		String timeStr = new String();
		if (hour < 10) {
			timeStr += "0";
		}
		timeStr += hour + ":";
		if (minute < 10) {
			timeStr += "0";
		}
		timeStr += minute + ":";
		if (second < 10) {
			timeStr += "0";
		}
		timeStr += second;

		return timeStr;
	}

	public void updateTime() {
		hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		minute = Calendar.getInstance().get(Calendar.MINUTE);
		second = Calendar.getInstance().get(Calendar.SECOND);
	}

	public double plusFontSize(final Font font, final double width) {
		final String family = font.getFamily();
		double size = font.getSize();
		final Text preText = new Text();
		final Text text = new Text();
		text.setText(DEFAULT_TIMER_TEXT);
		preText.setText(DEFAULT_TIMER_TEXT);
		while (true) {
			text.setFont(Font.font(family, size));
			preText.setFont(Font.font(family, size + 1));
			if (width * 4 / 5 - text.getLayoutBounds().getWidth() < 0) {
				break;
			}
			size++;
		}
		return size;
	}

	public double minusFontSize(final Font font, final double width) {
		final String family = font.getFamily();
		double size = font.getSize();
		final Text preText = new Text();
		final Text text = new Text();
		text.setText(DEFAULT_TIMER_TEXT);
		preText.setText(DEFAULT_TIMER_TEXT);
		while (true) {
			text.setFont(Font.font(family, size));
			preText.setFont(Font.font(family, size - 1));
			if (width * 4 / 5 - text.getLayoutBounds().getWidth() > 0) {
				break;
			}
			size--;
		}
		return size;
	}

	public void setFontColor(final String fontColorName) {
		this.save(MenuKey.FONT_COLOR, fontColorName);
		this.fontColorName = fontColorName;
	}

	public void setBackgroundColor(final String backgroundColorName) {
		this.save(MenuKey.BACKGROUND_COLOR, backgroundColorName);
		this.backgroundColorName = backgroundColorName;
	}

	public void setFont(final Font font) {
		this.save(MenuKey.FONT_SIZE, String.valueOf(font.getSize()));
		this.save(MenuKey.FONT_STYLE, font.getFamily());
		this.font = font;
	}

	public void setClockType(final ClockType clockType) {
		this.save(MenuKey.CLOCK_TYPE, clockType.toString());
		this.clockType = clockType;
	}

	public void setMusic(final String music) {
		this.music = music;
	}

	public void setCustomMusic(final String customMusic) {
		this.customMusic = customMusic;
	}

	public void setAlarmHour(final int alarmHour) {
		this.alarmHour = alarmHour;
	}

	public void setAlarmMinute(final int alarmMinute) {
		this.alarmMinute = alarmMinute;
	}

	public String getFontColorName() {
		return fontColorName;
	}

	public int getFontSize() {
		return fontSize;
	}

	public String getBackgroundColorName() {
		return backgroundColorName;
	}

	public Font getFont() {
		return this.font;
	}

	public ClockType getClockType() {
		return this.clockType;
	}

	public long getUpdateSpeed() {
		synchronized (this) {
			return updateSpeed;
		}
	}

	public String getMusic() {
		return this.music;
	}

	public String getCustomMusic() {
		return this.customMusic;
	}

	public int getAlarmHour() {
		return this.alarmHour;
	}

	public int getAlarmMinute() {
		return this.alarmMinute;
	}

	public void changeUpdateSpeed(ScreenMode mode) {
		synchronized (this) {
			switch (mode) {
			case CLOCK:
				this.updateSpeed = 1000;
				break;
			case TETRIS:
				this.updateSpeed = 1000 * 10;
				break;
			default:
				break;
			}

		}
	}

	public void playMusic(String musicPath) {
		if (musicPath != null
				&& Objects.equals(Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
						this.getAlarmHour())
				&& Objects.equals(Calendar.getInstance().get(Calendar.MINUTE),
						this.getAlarmMinute())
				&& Objects.equals(Calendar.getInstance().get(Calendar.SECOND), 0)) {
			Media media = new Media(musicPath);
			MediaPlayer mplayer = new MediaPlayer(media);
			mplayer.play();
		}
	}
}
