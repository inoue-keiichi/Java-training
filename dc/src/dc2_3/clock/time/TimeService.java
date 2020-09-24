package dc2_3.clock.time;

import static dc2_3.clock.popup.PopupMenuUtility.*;

import java.awt.Color;
import java.awt.FontMetrics;
import java.util.Calendar;

import dc2_3.interfaces.Service;

public class TimeService implements Service {
	private FontMetrics fontMetrics;
	private int fontMetricsOffsetX;
	private int fontMetricsOffsetY;
	private Color backgroundColor = Color.black;
	private Color fontColor = Color.green;
	private int fontSize = 100;
	private String fontName = "Dialog";
	private int hour;
	private int minute;
	private int second;

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

	public void convertCommand(String popupMenuName, String command) {
		switch (popupMenuName) {
		case "Font":
			this.fontName = command;
			break;
		case "Font Size":
			this.fontSize = Integer.parseInt(command);
			break;
		case "Font Color":
			this.fontColor = colorConverter(command);
			break;
		case "Background Color":
			this.backgroundColor = colorConverter(command);
			break;
		default:
		}
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setFont(String fontName) {
		this.fontName = fontName;
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

	public String getFont() {
		return fontName;
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

	public FontMetrics getFontMetrics() {
		return fontMetrics;
	}

	public void setFontMetrics(FontMetrics fontMetrics) {
		this.fontMetrics = fontMetrics;
	}

	public void setFontMetricsOffsetX(int x) {
		this.fontMetricsOffsetX = x;
	}

	public void setFontMetricsOffsetY(int y) {
		this.fontMetricsOffsetY = y;
	}

	public int getFontMetricsOffsetX() {
		return this.fontMetricsOffsetX;
	}

	public int getFontMetricsOffsetY() {
		return this.fontMetricsOffsetY;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = Integer.parseInt(fontSize);
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
