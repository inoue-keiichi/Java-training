package dc1_2;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;

public class PropertyDialogService {
	static final String[] FONT_STYLE_ARRAY = { "Plain", "Bold", "Italic" };
	static final String[] FONT_SIZE_ARRAY = { "100", "120", "130", "140", "150", "160", "170", "180", "190", "200", "250",
			"200" };
	static final String[] COLOR_ARRAY = { "black", "blue", "cyan", "darkGray", "gray", "green", "lightGray", "magenta",
			"orange", "pink", "red", "white", "yellow" };

	static Choice fontChoice = setChoice(PropertyDialogService.FONT_STYLE_ARRAY);
	static Choice fontSizeChoice = setChoice(PropertyDialogService.FONT_SIZE_ARRAY);
	static Choice fontColorChoice = setChoice(PropertyDialogService.COLOR_ARRAY);
	static Choice backgroundColorChoice = setChoice(PropertyDialogService.COLOR_ARRAY);
	
	/**
	 * プルダウンの選択肢をセットしてChoiceオブジェクトを返します。
	 * 
	 * @param array 選択肢の配列
	 * @return Choiceのオブジェクト
	 */
	public static final Choice setChoice(String[] array) {
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
		case "lightGreen":
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
}
