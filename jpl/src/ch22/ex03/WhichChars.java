package ch22.ex03;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map.Entry;

public class WhichChars {
	private HashMap<String, BitSet> used = new HashMap<>();

	public WhichChars(String str) {
		String upperByte;
		BitSet bitSet;
		for (int i = 0; i < str.length(); i++) {
			upperByte = Integer.toHexString((byte) (str.charAt(i) >>> 8));
			if (used.containsKey(upperByte)) {
				bitSet = used.get(upperByte);
			} else {
				bitSet = new BitSet();
			}
			bitSet.set(str.charAt(i));
			used.put(upperByte, bitSet);
		}
	}

	public String toString() {
		String desc = "[";
		for (Entry<String, BitSet> entry : used.entrySet()) {
			for (int i = entry.getValue().nextSetBit(0); i >= 0; i = entry.getValue().nextSetBit(i + 1)) {
				desc += (char) i;
			}
		}
		return desc + "]";
	}

	public static void main(String[] args) {
		WhichChars whichChars = new WhichChars("test ぴ づ ひ");
		System.out.println(whichChars.toString());
	}
}
