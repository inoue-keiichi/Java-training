package ch19.ex01;

/**
 * An <code>LinkedList</code>
 * 
 * @version 1.1
 * @author user
 * @since 1.0
 */
public class LinkedList {
	private Object obj;
	private LinkedList next;
	private static final LinkedList first = null;

	/**
	 * Creates a new LinkedList.
	 * 
	 * @param obj  The value of the LinkedList.
	 * @param next The next of the LinkedList.
	 */
	public LinkedList(Object obj, LinkedList next) {
		this.obj = obj;
		this.next = next;
	}

	/**
	 * Counts the number of the nodes in the LinkedList.
	 * 
	 * @return The number of the nodes the LinkedList.
	 */
	public int count() {
		LinkedList nextNode = next;
		int count = 1;

		while (nextNode != null) {
			count++;
			nextNode = nextNode.next;
		}
		return count;
	}

	/**
	 * Returns a string of the form <code>name=value</code>.
	 */
	public String toString() {
		String str = obj.toString();
		return str;
	}

	/**
	 * Returns the first node of the LinkedList.
	 * 
	 * @return the first node.
	 */
	public static LinkedList getFirst() {
		return first;
	}

	/**
	 * Returns the object of the node.
	 * 
	 * @return The object.
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * Returns the next LinkedList.
	 * 
	 * @return The next LinkedList.
	 */
	public LinkedList getNext() {
		return next;
	}

	/**
	 * Sets a object.
	 * 
	 * @param obj The value of the LinkedList.
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}

	/**
	 * Sets a LinkedList.
	 * 
	 * @param next the next node of the LinkedList.
	 */
	public void setNext(LinkedList next) {
		this.next = next;
	}
}
