package dc1_2;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

public class ClockService {
	private static final ClockService clockService = new ClockService();

	private Color fontColor = Color.green;
	private Color backgroundColor = Color.black;
	private int fontSize = 100;
	private int font = Font.PLAIN;

	private int hour;
	private int minute;
	private int second;

	public static ClockService getInstance() {
		return clockService;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setFont(int font) {
		this.font = font;
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

	public Color getFontColor() {
		return fontColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public int getFontSize() {
		return fontSize;
	}

	public int getFont() {
		return font;
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
}
