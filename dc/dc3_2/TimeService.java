package dc3_2;

import java.util.Calendar;

import javafx.scene.text.Font;

public class TimeService {
	private static final TimeService timeService = new TimeService();

	public static TimeService getInstance() {
		return timeService;
	}

	public enum MenuKey {
		FONT_STYLE("fontStyle"), FONT_SIZE("fontSize"), FONT_COLOR("fontColor"), BACKGROUND_COLOR("backgroundColor");

		private String name;

		MenuKey(final String name) {
			this.name = name;
		}
	}

	private String backgroundColor;
	private String fontColor;
	private int fontSize;
	private Font font;
	private int hour;
	private int minute;
	private int second;

	private TimeService() {
		font = Font.font(60);
		fontColor = "green";
		backgroundColor = "black";
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

	public void setFontColor(final String fontColorName) {
		this.fontColor = fontColorName;
	}

	public void setBackgroundColor(final String backgroundColorName) {
		this.backgroundColor = backgroundColorName;
	}

	public String getFontColor() {
		return fontColor;
	}

	public int getFontSize() {
		return fontSize;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public Font getFont() {
		return this.font;
	}

	public void setFont(final Font font) {
		this.font = font;
	}
}
