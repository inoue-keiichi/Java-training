package ch10.ex01;

public class Converter {

	private static String replaceEscapeSequence(final Character chr) {
		if (chr == '\n') {
			return "\\n";
		} else if (chr == '\t') {
			return "\\t";
		} else if (chr == '\b') {
			return "\\b";
		} else if (chr == '\r') {
			return "\\r";
		} else if (chr == '\f') {
			return "\\f";
		} else if (chr == '\\') {
			return "\\\\";
		} else if (chr == '\'') {
			return "\\'";
		} else if (chr == '\"') {
			return "\\\"";
		} else {
			return chr.toString();
		}
	}

	public static String escapeSequencesConverter(final String str) {
		String converteredStr = new String();
		for (int i = 0; i < str.length(); i++) {
			converteredStr += replaceEscapeSequence(str.charAt(i));
		}
		return converteredStr;
	}

	public static void main(String[] args) {
		System.out.println(escapeSequencesConverter("asfavead\n\t\\112234323242"));
	}
}
