package dc2_2.clock.menu;

import java.awt.Choice;
import java.awt.Color;
import java.awt.GraphicsEnvironment;

import dc2_2.interfaces.Service;

public class PropertyService implements Service {
	private final int[] FONT_SIZE_ARRAY = { 100, 120, 130, 140, 150, 160, 170, 180, 190, 200,
			250, 300 };
	private final String[] FONT_COLOR_ARRAY = { "green", "black", "blue", "cyan", "darkGray", "gray", "lightGray",
			"magenta", "orange", "pink", "red", "white", "yellow" };
	private final String[] BACKGROUND_COLOR_ARRAY = { "black", "blue", "cyan", "darkGray", "gray", "green", "lightGray",
			"magenta", "orange", "pink", "red", "white", "yellow" };

	//	private Choice fontChoice = setChoice(
	//			GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
	//	private Choice fontSizeChoice = setChoice(FONT_SIZE_ARRAY);
	//	private Choice fontColorChoice = setChoice(FONT_COLOR_ARRAY);
	//	private Choice backgroundColorChoice = setChoice(BACKGROUND_COLOR_ARRAY);

	private Color fontColorTip;
	private Color backgroundColorTip;

	/**
	 * プルダウンの選択肢をセットしてChoiceオブジェクトを返します。
	 *
	 * @param array 選択肢の配列
	 * @return Choiceのオブジェクト
	 */
	public final Choice setChoice(String[] array) {
		Choice choice = new Choice();
		for (String element : array) {
			choice.add(element);
		}
		return choice;
	}

	public final String[] getFonts() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	}

	public final int[] getFontSizes() {
		return FONT_SIZE_ARRAY;
	}

	public final String[] getFontColorNames() {
		return FONT_COLOR_ARRAY;
	}

	public final String[] getBackgroundColorNames() {
		return BACKGROUND_COLOR_ARRAY;
	}

	public final Color getFontColorTip() {
		return fontColorTip;
	}

	public final void setFontColorTip(Color fontColorTip) {
		this.fontColorTip = fontColorTip;
	}

	public final Color getBackgroundColorTip() {
		return fontColorTip;
	}

	public final void setBackgroundColorTip(Color backgroundColorTip) {
		this.backgroundColorTip = backgroundColorTip;
	}

}
