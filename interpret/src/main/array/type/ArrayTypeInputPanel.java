package main.array.type;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.ErrorHandler;
import main.MemberPrintGenerator;
import main.array.ArrayReflectionService;
import main.array.member.ElementConstructorPrintGenerator;

public class ArrayTypeInputPanel extends JPanel implements ActionListener {
	private static final ArrayTypeInputPanel arrayTypeInputPanel = new ArrayTypeInputPanel();

	final ArrayReflectionService reflectionService = ArrayReflectionService.getInstance();
	final ElementConstructorPrintGenerator constructorPrintGenerator = ElementConstructorPrintGenerator.getInstance();
	final MemberPrintGenerator memberPrintGenerator = MemberPrintGenerator.getInstance();
	final ArrayCreatePrintGenerator arrayCreatePrintGenerator = ArrayCreatePrintGenerator.getInstance();

	final JTextField typeText = new JTextField(20);
	final JTextField sizeText = new JTextField(5);
	final JButton setBtn = new JButton("Create");

	public ArrayTypeInputPanel() {
		this.add(typeText);
		this.add(sizeText);
		this.add(setBtn);
		setBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		reflectionService.setArray(typeText.getText(), sizeText.getText());

		try {
			arrayCreatePrintGenerator.execute();
		} catch (ClassNotFoundException e1) {
			ErrorHandler.getInstance().execute(e1);
		}
//		 メンバータブを表示
		try {
			memberPrintGenerator.execute();
		} catch (Throwable e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
	}

	public static ArrayTypeInputPanel getInstance() {
		return arrayTypeInputPanel;
	}
}
