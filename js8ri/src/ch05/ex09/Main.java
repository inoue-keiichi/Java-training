package ch05.ex09;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(final String[] srgs) {
		getZoneOffsetMap().entrySet().forEach(e -> {
			System.out.format("%32s: %6s", e.getKey(), e.getValue());
			System.out.println();
		});
	}

	public static Map<String, String> getZoneOffsetMap() {
		final Map<String, String> result = new HashMap<>();
		ZoneId.getAvailableZoneIds().stream().forEach(zoneId -> {
			if (ZonedDateTime.now(ZoneId.of(zoneId)).getOffset().toString().matches(".*[0-2][0-9]:[1-5][0-9]")) {
				result.put(zoneId, ZonedDateTime.now(ZoneId.of(zoneId)).getOffset().toString());
			} else if (ZonedDateTime.now(ZoneId.of(zoneId)).getOffset().toString().matches(".*[0-2][0-9]:0[1-9]")) {
				result.put(zoneId, ZonedDateTime.now(ZoneId.of(zoneId)).getOffset().toString());
			}
		});
		return result;
	}
}
