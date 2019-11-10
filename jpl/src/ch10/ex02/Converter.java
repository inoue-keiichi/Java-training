package ch10.ex02;

public class Converter {

	private static String replaceEscapeSequence(final Character chr) {
		switch(chr) {
		case '\n':
			return "\\n";
		case '\t':
			return "\\t";
		case '\b':
			return "\\b";
		case '\r':
			return "\\r";
		case '\f':
			return "\\f";
		case '\\':
			return "\\\\";
		case '\'':
			return "\\'";
		case '\"':
			return "\\\"";
		default:
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
		String s = "0mnn";
	}
}
