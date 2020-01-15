package ch16.ex12;

public enum Hand {
	Guu(0), Choki(1), Paa(2);

	private int id;

	Hand(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static Hand get(final int id) {
		Hand[] hands = Hand.values();
		for (Hand hand : hands) {
			if (hand.getId() == id) {
				return hand;
			}
		}
		return null;
	}

}