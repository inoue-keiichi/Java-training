package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.value.member.InstancePanel;
import main.value.member.MemberPanel;

public class InterpretView extends JFrame implements Runnable, ItemListener, ComponentListener {
	private final Thread thread = new Thread(this);
	// ログテキストエリア
	public final View logTextArea;
	public final JPanel instancePanel;
	public final JPanel memberPanel;

	private final AutowiredService service;
	private final AutowiredGenerator generator;

	public InterpretView() {
		this.service = new AutowiredService();
		this.generator = new AutowiredGenerator(service);

		final JPanel pane = new JPanel(new GridBagLayout());
		pane.setBackground(java.awt.Color.gray);
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
		this.instancePanel = (JPanel) new InstancePanel(generator, service).view;
		pane.add(this.instancePanel, gbc);
		// メンバタブ
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		this.memberPanel = (JPanel) new MemberPanel(generator, service).view;
		pane.add(this.memberPanel, gbc);
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
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new InterpretWindowAdapter(service));
		this.setVisible(true);
		this.setMinimumSize(new Dimension(1700, 450));
		service.interpretViewService.x = this.getBounds().x;
		service.interpretViewService.y = this.getBounds().y;
		service.interpretViewService.dimension = this.getSize();
		this.addComponentListener(this);
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

	@Override
	public void componentResized(ComponentEvent e) {
		this.service.interpretViewService.dimension = this.getSize();

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		this.service.interpretViewService.x = this.getBounds().x;
		this.service.interpretViewService.y = this.getBounds().y;
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}
}