package ch22.ex04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

import ch11.ex03.Attr;
import ch11.ex03.Attributed;

public class AttributedImpl extends Observable implements Attributed<String> {
	private List<Attr<String>> list = new ArrayList<>();

	@Override
	public void add(Attr<String> newAttr) {
		list.add(newAttr);
	}

	@Override
	public Attr<String> find(String attrName) {
		return null;
	}

	@Override
	public Attr<String> remove(String attrName) {
		for (int i = 0; i < list.size(); i++) {
			if (Objects.equals(list.get(i).getName(), attrName)) {
				final Attr<String> attr = list.remove(i);
				this.setChanged();
				this.notifyObservers(attr.getName());
				return attr;
			}
		}
		return null;
	}

	@Override
	public Iterator<Attr<String>> attrs() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
