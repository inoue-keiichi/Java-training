package dc1_3;

import java.awt.Color;
import static dc1_3.PopupMenuUtility.*;

public class ClockService {
	private static final ClockService clockService = new ClockService();

	private Color fontColor = Color.green;
	private Color backgroundColor = Color.black;
	private int fontSize = 100;
	private String fontName = "Dialog";

	private int hour;
	private int minute;
	private int second;

	private ClockService() {

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

	public Color getBackgroundColor() {
		return backgroundColor;
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
}
