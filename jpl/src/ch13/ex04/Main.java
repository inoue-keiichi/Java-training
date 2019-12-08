package ch13.ex04;

public class Main {
	public static void main(String[] arg) {
		final String str = "Integer 2019\nBoolean true\nCharacter s\n";
		try {
			StringUtil.readTypeAndValue(str);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
