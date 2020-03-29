package main.value.member;

import static java.awt.GridBagConstraints.*;
import static main.Autowired.*;
import static main.StringUtils.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.ArgText;
import main.Autowired;
import main.ItemComparator;
import main.Observer;
import main.PrintGenerator;

public class FieldPanel extends JPanel implements Observer, ActionListener {
	private final JComboBox<String> fieldComboBox = new JComboBox<>();
	private final ArgText fieldText = new ArgText();
	private final JButton updateBtn = new JButton("Update");
	private final GridBagConstraints gbc = new GridBagConstraints();

	public FieldPanel() {
		Autowired.fieldPrintGenerator.addObserver(this);

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = WEST;
		this.add(new JLabel("Field: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = EAST;
		this.add(fieldComboBox, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = WEST;
		this.add(new JLabel("Value: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = EAST;
		this.add(fieldText.text, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = EAST;
		this.add(updateBtn, gbc);
		updateBtn.addActionListener(this);
	}

	public JComboBox<String> getFieldComboBox() {
		return this.fieldComboBox;
	}

	public String getInputText() {
		return this.fieldText.text.getText();
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		final Object instance = memberService.getInstance();
		// インスタンスからFieldを取得してプルダウンに表示する
		final JComboBox<String> fieldComboBox = this.fieldComboBox;
		List<Field> fieldList = Arrays.asList(instance.getClass().getDeclaredFields());
		fieldList = new ArrayList<Field>(fieldList);
		// superClassのfieldもあれば追加する
		Class<?> clazz = instance.getClass();
		while (Objects.nonNull(clazz) && !Objects.equals(clazz.getName(), Object.class.getName())) {
			clazz = clazz.getSuperclass();
			Field[] superFields = clazz.getDeclaredFields();
			List<Field> superFieldList = Arrays.asList(superFields);
			fieldList.addAll(superFieldList);
		}
		fieldList.sort(new ItemComparator());
		// fieldを保存
		final Field[] fields = fieldList.toArray(new Field[fieldList.size()]);
		reflectionService.setFields(fields);
		// 元々あった選択肢を削除
		if (fieldComboBox.getItemCount() != 0) {
			fieldComboBox.removeAllItems();
		}
		for (Field field : fields) {
			fieldComboBox.addItem(getNameAndParameter(field));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Autowired.memberService.setFieldPanel(this);
			Autowired.fieldUpdatePrintGenerator.execute();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
			Autowired.errorHandler.execute(e1);
		}
	}
}
