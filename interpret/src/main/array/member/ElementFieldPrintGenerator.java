package main.array.member;

import static main.StringUtils.getNameAndParameter;
import java.lang.reflect.Field;
import javax.swing.JComboBox;

import main.PrintGenerator;
import main.array.ArrayReflectionService;

public class ElementFieldPrintGenerator extends PrintGenerator {
	private static final ElementFieldPrintGenerator fieldPrintGenerator = new ElementFieldPrintGenerator();

	private ArrayReflectionService reflectionService = ArrayReflectionService.getInstance();
	private ElementFieldPanel fieldPanel = ElementFieldPanel.getInstance();

	private ElementFieldPrintGenerator() {

	}

	@Override
	public void execute() {
		// インスタンスからFieldを取得してプルダウンに表示する
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

	public static ElementFieldPrintGenerator getInstance() {
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
