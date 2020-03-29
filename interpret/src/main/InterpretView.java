package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.value.member.InstancePanel;
import main.value.member.MemberPanel;

public class InterpretView extends JFrame implements Runnable, ItemListener {
	private final Thread thread = new Thread(this);
	// ログテキストエリア
	public final View logTextArea;

	public InterpretView() {
		AutowiredService service = new AutowiredService();
		AutowiredGenerator generator = new AutowiredGenerator(service);

		final JPanel pane = new JPanel(new GridBagLayout());
		this.setContentPane(pane);
		// InterpretViewの配置決め
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0, 10, 10, 10);

		// インスタンスパネル
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		pane.add(new InstancePanel(generator, service).view, gbc);
		// メンバタブ
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		pane.add(new MemberPanel(generator, service).view, gbc);
		// ログパネル
		this.logTextArea = new LogTextArea(generator, service);
		JTextArea textArea = (JTextArea) this.logTextArea.view;
		textArea.setEditable(false);
		final JScrollPane scrollpane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		pane.add(scrollpane, gbc);

		addWindowListener(new InterpretWindowAdapter());
		this.setVisible(true);
		this.setMinimumSize(new Dimension(1200, 450));
	}

	@Override
	public void run() {
		while (true) {

		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		String cmd = (String) e.getItem();
	}
}