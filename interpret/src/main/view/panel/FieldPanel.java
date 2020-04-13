package main.view.panel;

import static java.awt.GridBagConstraints.*;
import static main.utils.StringUtils.*;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.di.AutowiredGenerator;
import main.di.AutowiredService;
import main.generator.ErrorHandler;
import main.generator.FieldUpdatePrintGenerator;
import main.generator.PrintGenerator;
import main.service.MemberService;
import main.service.ReflectionService;
import main.view.Observer;
import main.view.View;
import main.view.button.ObserverButton;
import main.view.field.ArgText;

public class FieldPanel extends View implements Observer, ActionListener {
	private final JComboBox<String> fieldComboBox;
	private final ArgText fieldText;
	private final ObserverButton updateBtn;
	private final GridBagConstraints gbc;

	private final ReflectionService reflectionService;
	private final MemberService memberService;
	private final ErrorHandler errorHandler;
	private final FieldUpdatePrintGenerator fieldUpdatePrintGenerator;

	public FieldPanel(final AutowiredGenerator generator, final AutowiredService service) {
		super(new JPanel(), generator, service);
		this.reflectionService = this.service.reflectionService;
		this.memberService = this.service.memberService;
		this.errorHandler = this.generator.errorHandler;
		this.fieldUpdatePrintGenerator = this.generator.fieldUpdatePrintGenerator;
		this.generator.fieldPrintGenerator.addObserver(this);

		//構成要素
		fieldComboBox = new JComboBox<>();
		fieldComboBox.setPreferredSize(new Dimension(400,30));
		fieldText = new ArgText(this.generator.errorHandler);
		updateBtn = new ObserverButton("Update");
		this.generator.fieldPrintGenerator.addObserver(updateBtn);

		GridBagLayout layout = new GridBagLayout();
		this.view.setLayout(layout);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = WEST;
		this.view.add(new JLabel("Field: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = EAST;
		this.view.add(fieldComboBox, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = WEST;
		this.view.add(new JLabel("Value: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = EAST;
		this.view.add(fieldText.text, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = EAST;
		this.view.add(updateBtn.btn, gbc);
		updateBtn.btn.addActionListener(this);
	}

	public JComboBox<String> getFieldComboBox() {
		return this.fieldComboBox;
	}

	public String getInputText() {
		return this.fieldText.text.getText();
	}

	public void clearField() {
		this.fieldComboBox.removeAllItems();
		this.fieldText.text.setText("");
		this.updateBtn.btn.setEnabled(false);
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
		// fieldを保存
		final Map<String, Field> fieldMap = new HashMap<>();
		for (Field field : fieldList) {
			fieldMap.put(getNameAndParameter(field), field);
		}
		reflectionService.setFieldMap(fieldMap);
		// 元々あった選択肢を削除
		if (fieldComboBox.getItemCount() != 0) {
			fieldComboBox.removeAllItems();
		}
		// Value削除
		this.fieldText.text.setText("");
		// プルダウンにfield名を追加
		final List<String> fieldKeys = new ArrayList<>(fieldMap.keySet());
		Collections.sort(fieldKeys);
		for (String key : fieldKeys) {
			fieldComboBox.addItem(key);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			memberService.setFieldPanel(this);
			fieldUpdatePrintGenerator.execute();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
			errorHandler.execute(e1);
		}
	}
}
