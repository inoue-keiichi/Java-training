package main.array.member;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.ErrorHandler;
import main.MemberPrintGenerator;
import main.value.ReflectionService;
import main.value.member.ConstructorPrintGenerator;
import main.value.type.ObjectTypeInputPanel;

public class ConstructorDialogPrintGenerator extends JPanel implements ActionListener {
	private static final ConstructorDialogPrintGenerator objectTypeInputPanel = new ConstructorDialogPrintGenerator();

	final ReflectionService reflectionService = ReflectionService.getInstance();
	final ConstructorPrintGenerator constructorPrintGenerator = ConstructorPrintGenerator.getInstance();
	final MemberPrintGenerator memberPrintGenerator = MemberPrintGenerator.getInstance();

	final JTextField typeText = new JTextField(30);
	final JButton setBtn = new JButton("Set");

	public ConstructorDialogPrintGenerator() {
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		layout.setConstraints(typeText, constraints);
		this.add(typeText);

		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(setBtn, constraints);
		this.add(setBtn);
		setBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			reflectionService.setClazz(typeText.getText());
		} catch (ClassNotFoundException e1) {
			ErrorHandler.getInstance().execute(e1);
		}
		// コンストラクタの項目を表示する
		constructorPrintGenerator.execute();
		// メンバータブを表示する
		try {
			memberPrintGenerator.execute();
		} catch (Throwable e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
	}

	public static ConstructorDialogPrintGenerator getInstance() {
		return objectTypeInputPanel;
	}
}
