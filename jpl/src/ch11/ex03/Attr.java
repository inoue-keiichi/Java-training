package ch11.ex03;

public class Attr<V> {
	private final String name;
	private V value = null;

	public Attr(String name) {
		this.name = name;
	}
	
	public Attr(String name, V value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public V getValue() {
		return value;
	}
	
	public V setValue(V newValue) {
		V oldVal = value;
		value = newValue;
		return oldVal;
	}
	
	public String toString() {
		return name + " ='" + value.toString() + "'";
	}
}
