package dc1_2;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;

public class MenuService {
	private static final MenuService menuService = new MenuService();
	private final String[] FONT_STYLE_ARRAY = { "Plain", "Bold", "Italic" };
	private final String[] FONT_SIZE_ARRAY = { "100", "120", "130", "140", "150", "160", "170", "180", "190", "200",
			"250", "300" };
	private final String[] FONT_COLOR_ARRAY = { "green", "black", "blue", "cyan", "darkGray", "gray", "lightGray",
			"magenta", "orange", "pink", "red", "white", "yellow" };
	private final String[] BACKGROUND_COLOR_ARRAY = { "black", "blue", "cyan", "darkGray", "gray", "green", "lightGray",
			"magenta", "orange", "pink", "red", "white", "yellow" };

	private Choice fontChoice = setChoice(FONT_STYLE_ARRAY);
	private Choice fontSizeChoice = setChoice(FONT_SIZE_ARRAY);
	private Choice fontColorChoice = setChoice(FONT_COLOR_ARRAY);
	private Choice backgroundColorChoice = setChoice(BACKGROUND_COLOR_ARRAY);

	private MenuService() {

	}

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

	/**
	 *   文字列を整数型に変換して返します。
	 * 
	 * @param num 文字列
	 * @return 整数
	 */
	public final int intConverter(String num) {
		return Integer.parseInt(num);
	}

	/**
	 * フォントの名前を引数に渡して、該当するFontのフィールド値を返します。
	 * 
	 * @param fontStyle フォントスタイル
	 * @return フォントスタイルを表す整数
	 */
	public final int fontConverter(String fontStyle) {
		int font;
		switch (fontStyle) {
		case "Plain":
			font = Font.PLAIN;
			break;
		case "Bold":
			font = Font.BOLD;
			break;
		case "Italic":
			font = Font.ITALIC;
			break;
		default:
			font = Font.PLAIN;
		}
		return font;
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

	public static final MenuService getInstance() {
		return menuService;
	}

	public final Choice getFontChoice() {
		return fontChoice;
	}

	public final Choice getFontSizeChoice() {
		return fontSizeChoice;
	}

	public final Choice getFontColorChoice() {
		return fontColorChoice;
	}

	public final Choice getBackgroundColorChoice() {
		return backgroundColorChoice;
	}

}
