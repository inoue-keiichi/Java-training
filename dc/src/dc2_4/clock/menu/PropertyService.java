package dc2_4.clock.menu;

import java.awt.Choice;
import java.awt.GraphicsEnvironment;

import dc2_4.interfaces.Service;

public class PropertyService implements Service {
	private final int[] FONT_SIZE_ARRAY = { 100, 120, 130, 140, 150, 160, 170, 180, 190, 200,
			250, 300 };
	private final String[] COLOR_ARRAY = { "black", "blue", "cyan", "darkGray", "gray", "green", "lightGray",
			"magenta", "orange", "pink", "red", "white", "yellow" };

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

	public final String[] getColorNames() {
		return COLOR_ARRAY;
	}
}
