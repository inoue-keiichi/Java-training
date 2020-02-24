package main;

import java.awt.TextArea;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.value.ReflectionService;

public class InstanceDialog extends JDialog {

	public InstanceDialog(final Object instance) {
		this.setSize(400, 800);
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		this.add(scrollPane);

		StringBuilder sb;
		try {
			sb = StringUtils.printClass(instance);
			textArea.setText(sb.toString());
		} catch (SecurityException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
