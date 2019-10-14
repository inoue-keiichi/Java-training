package ch02.ex14;

public class LinkedList {
	//firstは、LinkedListで辿った最後の要素にしたいためnullのままにしたい。
	private static final LinkedList first = null;
	
	private Object obj;
	private LinkedList next;

	public LinkedList(Object obj, LinkedList next) {
		this.obj = obj;
		this.next = next;
	}
	
	public LinkedList() {
		
	}
	
	//firstは、LinkedListの終点にするためにあるだけなので、アクセッサーメソッド不要。
	//なお、要素の削除や挿入を行うために、nextはアクセッサーメソッドが必要。
	public static LinkedList getFirst() {
		return first;
	}
	
	public Object getObj() {
		return obj;
	}
	
	public LinkedList getNext() {
		return next;
	}
	
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public void setNext(LinkedList next) {
		this.next = next;
	}

	public String toString() {
		String str = obj.toString();
		return str;
	}
}
