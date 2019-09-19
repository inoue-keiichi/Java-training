package src.ch01.ex15;

public interface AddDelete extends Lookup {
	void add(String name, Object value);
	void remove();
}
