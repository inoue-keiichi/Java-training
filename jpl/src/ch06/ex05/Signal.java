package ch06.ex05;

enum Signal {
	RED {
		Color getColor() {
			return new Color("RED");
		}
	},
	YELLOW {
		Color getColor() {
			return new Color("YELLOW");
		}
	},
	BLUE {
		Color getColor() {
			return new Color("BLUE");
		}
	};

	class Color {
		private String name;

		Color(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	abstract Color getColor();
}
