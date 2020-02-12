package main.value.member;

import static main.StringUtils.getNameAndParameter;
import java.lang.reflect.Field;
import javax.swing.JComboBox;

import main.PrintGenerator;
import main.value.ReflectionService;

public class FieldPrintGenerator extends PrintGenerator {
	private static final FieldPrintGenerator fieldPrintGenerator = new FieldPrintGenerator();

	private ReflectionService reflectionService = ReflectionService.getInstance();
	private FieldPanel fieldPanel = FieldPanel.getInstance();

	private FieldPrintGenerator() {

	}

	@Override
	public void execute() {
		//インスタンスからFieldを取得してプルダウンに表示する
		JComboBox<String> fieldComboBox = fieldPanel.getFieldComboBox();
		Field[] fields = reflectionService.getNewInstance().getClass().getDeclaredFields();
		// fieldを保存
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

	public static FieldPrintGenerator getInstance() {
		return fieldPrintGenerator;
	}

	@Override
	public String getLog() {
		if (fieldPanel.getFieldComboBox().getItemCount() < 1) {
			return "";
		}
		return "the fields were created.\n";
	}
}
