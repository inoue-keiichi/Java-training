package main.view.combobox;

import javax.swing.JComboBox;

import main.generator.PrintGenerator;
import main.view.Observer;

public class IndexComboBox implements Observer {
	public JComboBox<Integer> comboBox = new JComboBox<>();

	@Override
	public void update(PrintGenerator printGenerator) {
		int length = (int) printGenerator.getItem();
		// 追加前にItemを全部削除
		comboBox.removeAllItems();;
		for (int i = 0; i < length; i++) {
			comboBox.addItem(i);
		}
	}
}
