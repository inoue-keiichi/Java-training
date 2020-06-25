package dc2_4.clock.utils;

import java.awt.Color;
import java.awt.Font;

public class ConverterUtils {
	/**
	 *   文字列を整数型に変換して返します。
	 *
	 * @param num 文字列
	 * @return 整数
	 */
	public static final int intConverter(String num) {
		return Integer.parseInt(num);
	}

	/**
	 * フォントの名前を引数に渡して、該当するFontのフィールド値を返します。
	 *
	 * @param fontStyle フォントスタイル
	 * @return フォントスタイルを表す整数
	 */
	public static final int fontConverter(String fontStyle) {
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
	public static final Color colorConverter(String colorName) {
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
}
