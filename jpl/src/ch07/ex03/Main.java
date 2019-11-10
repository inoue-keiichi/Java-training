package ch07.ex03;

public class Main {
	public static void main(String[] args) {
		Pascal pascal = new Pascal();
		pascal.ShowPascalMatrix();
		
		pascal = new Pascal(4);
		pascal.ShowPascalMatrix();
	}
}
