package dc3_1;

import java.awt.Color;
import java.awt.FontMetrics;
import java.io.IOException;
import java.util.Calendar;
import java.util.prefs.Preferences;

import dc2_4.clock.menu.PropertyUtility;
import dc2_4.interfaces.Service;
import javafx.scene.text.Font;

public class TimeService implements Service {
	public enum MenuKey {
		FONT_STYLE("fontStyle"), FONT_SIZE("fontSize"), FONT_COLOR("fontColor"), BACKGROUND_COLOR("backgroundColor");

		private String name;

		MenuKey(final String name) {
			this.name = name;
		}
	}

	private final Preferences prefs;
	private FontMetrics fontMetrics;
	private int fontMetricsOffsetX;
	private int fontMetricsOffsetY;
	private Color backgroundColor;
	private Color fontColor;
	private int fontSize;
	private String fontStyle;
	private String fontFamily;
	private Font font;
	private int hour;
	private int minute;
	private int second;

	public TimeService() {
		prefs = Preferences.userNodeForPackage(this.getClass());
		try {
			fontColor = PropertyUtility.colorConverter(load(MenuKey.FONT_COLOR));
			backgroundColor = PropertyUtility.colorConverter(load(MenuKey.BACKGROUND_COLOR));
			fontSize = Integer.parseInt(load(MenuKey.FONT_SIZE));
			fontStyle = load(MenuKey.FONT_STYLE);
			font = Font.font(60);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
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
		setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		setMinute(Calendar.getInstance().get(Calendar.MINUTE));
		setSecond(Calendar.getInstance().get(Calendar.SECOND));
	}

	public void setFontColor(String fontColor) {
		this.save(MenuKey.FONT_COLOR, fontColor);
		this.fontColor = PropertyUtility.colorConverter(fontColor);
	}

	public void setBackgroundColor(String backgroundColor) {
		this.save(MenuKey.BACKGROUND_COLOR, backgroundColor);
		this.backgroundColor = PropertyUtility.colorConverter(backgroundColor);
	}

	public void setFontSize(int fontSize) {
		this.save(MenuKey.FONT_SIZE, String.valueOf(fontSize));
		this.fontSize = fontSize;
	}

	public void setFont(String fontStyle) {
		this.save(MenuKey.FONT_STYLE, fontStyle);
		this.fontStyle = fontStyle;
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

	public int getFontSize() {
		return fontSize;
	}

	//	public String getFont() {
	//		return fontStyle;
	//	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public FontMetrics getFontMetrics() {
		return fontMetrics;
	}

	public void setFontMetrics(FontMetrics fontMetrics) {
		this.fontMetrics = fontMetrics;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = Integer.parseInt(fontSize);
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setFontMetricsOffsetX(int fontMetricsOffsetX) {
		this.fontMetricsOffsetX = fontMetricsOffsetX;
	}

	public int getFontMetricsOffsetX() {
		return this.fontMetricsOffsetX;
	}

	public void setFontMetricsOffsetY(int fontMetricsOffsetY) {
		this.fontMetricsOffsetY = fontMetricsOffsetY;
	}

	public int getFontMetricsOffsetY() {
		return this.fontMetricsOffsetY;
	}

	public Font getFont() {
		return this.font;
	}

	public void setFont(final String fontFamily, double size) {
		this.fontFamily = fontFamily;
		this.font = Font.font(fontFamily, size);
	}

	public void setFont(Font font) {
		this.font = font;
	}
}
