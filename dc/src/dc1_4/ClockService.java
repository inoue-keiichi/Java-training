package dc1_4;

import java.awt.Color;
import java.awt.FontMetrics;
import java.io.IOException;
import java.util.prefs.Preferences;

public class ClockService {
	private static final ClockService clockService = new ClockService();

	public enum MenuKey {
		FONT_STYLE("fontStyle"), FONT_SIZE("fontSize"), FONT_COLOR("fontColor"), BACKGROUND_COLOR("backgroundColor");

		private String name;

		MenuKey(final String name) {
			this.name = name;
		}
	}

	public enum FrameKey {
		FRAME_X("frameX"), FRAME_Y("frameY"), HEIGHT("frameHeight"), WIDTH("frameWidth");

		private String name;

		FrameKey(final String name) {
			this.name = name;
		}
	}

	private Preferences prefs;
	private Color fontColor;
	private Color backgroundColor;
	private int fontSize;
	private String fontStyle;
	private int hour;
	private int minute;
	private int second;

	private FontMetrics fontMetrics;
	private int height;
	private int width;
	private int frameX;
	private int frameY;

	private ClockService() {
		prefs = Preferences.userNodeForPackage(this.getClass());
		try {
			fontColor = colorConverter(load(MenuKey.FONT_COLOR));
			backgroundColor = colorConverter(load(MenuKey.BACKGROUND_COLOR));
			fontSize = Integer.parseInt(load(MenuKey.FONT_SIZE));
			fontStyle = load(MenuKey.FONT_STYLE);
			frameX = load(FrameKey.FRAME_X);
			frameY = load(FrameKey.FRAME_Y);
//			height = load(FrameKey.HEIGHT);
//			width = load(FrameKey.WIDTH);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
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

	public void save(final MenuKey key, final String value) {
		switch (key) {
		case FONT_STYLE:
			prefs.put(key.toString(), value);
			break;

		case FONT_SIZE:
			prefs.put(key.toString(), value);
			break;

		case FONT_COLOR:
			prefs.put(key.toString(), value);
			break;

		case BACKGROUND_COLOR:
			prefs.put(key.toString(), value);
			break;

		default:
			return;
		}
	}

	public void save(final FrameKey key, final int value) {
		switch (key) {
		case FRAME_X:
			prefs.putInt(key.toString(), value);
			break;

		case FRAME_Y:
			prefs.putInt(key.toString(), value);
			break;

		case HEIGHT:
			prefs.putInt(key.toString(), value);
			break;

		case WIDTH:
			prefs.putInt(key.toString(), value);
			break;

		default:
			return;
		}
	}

	public String load(final MenuKey key) throws IOException {
		switch (key) {
		case FONT_STYLE:
			return prefs.get(key.toString(), "Dialog");

		case FONT_SIZE:
			return prefs.get(key.toString(), "100");

		case FONT_COLOR:
			return prefs.get(key.toString(), "green");

		case BACKGROUND_COLOR:
			return prefs.get(key.toString(), "black");

		default:
			throw new IOException();
		}
	}

	public int load(final FrameKey key) throws IOException {
		switch (key) {
		case FRAME_X:
			return prefs.getInt(key.toString(), 0);

		case FRAME_Y:
			return prefs.getInt(key.toString(), 0);

		case HEIGHT:
			return prefs.getInt(key.toString(), 200);

		case WIDTH:
			return prefs.getInt(key.toString(), 400);

		default:
			throw new IOException();
		}
	}

	/**
	 * 色の名前を引数に渡して、該当するColorのフィールド値を返します。
	 * 
	 * @param colorName 色の名前
	 * @return Colorのフィールド値
	 */
	public final Color colorConverter(String colorName) {
		Color color;
		switch (colorName) {
		case "black":
			color = Color.black;
			break;
		case "blue":
			color = Color.blue;
			break;
		case "cyan":
			color = Color.cyan;
			break;
		case "darkGray":
			color = Color.darkGray;
			break;
		case "gray":
			color = Color.gray;
			break;
		case "green":
			color = Color.green;
			break;
		case "lightGray":
			color = Color.lightGray;
			break;
		case "magenta":
			color = Color.magenta;
			break;
		case "orange":
			color = Color.orange;
			break;
		case "pink":
			color = Color.pink;
			break;
		case "red":
			color = Color.red;
			break;
		case "white":
			color = Color.white;
			break;
		case "yellow":
			color = Color.yellow;
			break;
		default:
			color = Color.black;
			break;
		}
		return color;
	}

	public static ClockService getInstance() {
		return clockService;
	}

	public void setFontColor(String fontColor) {
		this.save(MenuKey.FONT_COLOR, fontColor);
		this.fontColor = colorConverter(fontColor);
	}

	public void setBackgroundColor(String backgroundColor) {
		this.save(MenuKey.BACKGROUND_COLOR, backgroundColor);
		this.backgroundColor = colorConverter(backgroundColor);
	}

	public void setFontSize(String fontSize) {
		this.save(MenuKey.FONT_SIZE, fontSize);
		this.fontSize = Integer.parseInt(fontSize);
	}

	public void setFont(String fontStyle) {
		this.save(MenuKey.FONT_STYLE, fontStyle);
		this.fontStyle = fontStyle;
	}

	public FontMetrics getFontMetrics() {
		return fontMetrics;
	}

	public void setFontMetrics(FontMetrics fontMetrics) {
		this.fontMetrics = fontMetrics;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		// this.save(FrameKey.HEIGHT, height);
		this.height = (int) (height * 1.2);
	}

	public void setWidth(int width) {
		// this.save(FrameKey.WIDTH, width);
		this.width = (int) (width * 1.2);
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public void setFrameX(int frameX) {
		this.save(FrameKey.FRAME_X, frameX);
		this.frameX = frameX;
	}

	public void setFrameY(int frameY) {
		this.save(FrameKey.FRAME_Y, frameY);
		this.frameY = frameY;
	}

	public Color getFontColor() {
		return fontColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public int getFontSize() {
		return fontSize;
	}

	public String getFont() {
		return fontStyle;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public int getFrameX() {
		return frameX;
	}

	public int getFrameY() {
		return frameY;
	}
}
