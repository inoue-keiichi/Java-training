package ch06.ex04;

enum Signal {
	RED("RED"), YELLOW("YELLOW"), BLUE("BLUE");

	private Color color;

	private Signal(String name) {
		this.color = new Color(name);
	}

	class Color {
		private String name;

		Color(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
