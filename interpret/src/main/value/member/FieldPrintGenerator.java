package main.value.member;

import static main.StringUtils.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.swing.JComboBox;

import main.Autowired;
import main.ItemComparator;
import main.PrintGenerator;
import main.value.ReflectionService;

public class FieldPrintGenerator extends PrintGenerator {
	private ReflectionService reflectionService = Autowired.reflectionService;
	private FieldPanel fieldPanel = Autowired.fieldPanel;

	@Override
	public void execute() {
	}

	public void execute(final Object instance) {
		// インスタンスからFieldを取得してプルダウンに表示する
		final JComboBox<String> fieldComboBox = fieldPanel.getFieldComboBox();
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
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		if (fieldPanel.getFieldComboBox().getItemCount() < 1) {
			return "";
		}
		return "the fields were created.\n";
	}
}
