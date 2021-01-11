package ch08.ex16;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	//public static final String CITY = "city";
	//public static final String STATE = "state";
	//public static final String POSTAL_CODE = "postalCode";
	enum Adress {
		CITY("city"), STATE("state"), POSTAL_CODE("postalCode");

		private final String name;

		Adress(final String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public static void main(final String[] args) {
		display(getAdress("NY, CA,00000"));
		System.out.println();
		display(getAdress("hoge, CA,000008888"));
	}

	private static void display(final Map<Adress, String> map) {
		map.entrySet()
				.forEach(entry -> System.out.println(entry.getKey().toString() + ": " + entry.getValue()));
	}

	public static Map<Adress, String> getAdress(final String input) {
		final Matcher matcher = Pattern.compile(
				"(?<city>[\\p{L}]+),\\s*(?<state>[A-Z]{2}),\\s*(?<postalCode>[0-9]{5}|[0-9]{9})")
				.matcher(input);
		if (!matcher.matches()) {
			return null;
		}

		return new LinkedHashMap<Adress, String>() {
			{
				put(Adress.CITY, matcher.group(Adress.CITY.toString()));
				put(Adress.STATE, matcher.group(Adress.STATE.toString()));
				put(Adress.POSTAL_CODE, matcher.group(Adress.POSTAL_CODE.toString()));
			}
		};
	}

}
