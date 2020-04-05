package main.generator;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import main.di.AutowiredService;
import main.service.MemberService;
import main.service.ReflectionService;
import main.view.panel.FieldPanel;

public class FieldUpdatePrintGenerator extends PrintGenerator {
	private final ReflectionService reflectionService;
	private final MemberService memberService;

	public FieldUpdatePrintGenerator(AutowiredService service) {
		super(service);
		reflectionService = service.reflectionService;
		memberService = service.memberService;
	}

	private String fieldName;
	private Object fieldValue;

	@Override
	public void execute()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// クリア
		this.fieldName = null;
		this.fieldValue = null;
		// text取得
		final FieldPanel fieldPanel = memberService.getFieldPanel();
		// fieldと値を保存
		reflectionService.setFieldArgments(fieldPanel.getInputText(),
				(String) fieldPanel.getFieldComboBox().getSelectedItem());
		// fieldを更新するための準備。
		final Field field = reflectionService.getFieldMap()
				.get((String) fieldPanel.getFieldComboBox().getSelectedItem());
		if (!Modifier.isStatic(field.getModifiers())) {
			// staticフィールドじゃない
			field.setAccessible(true);
		} else {
			// staticフィールドだとprivateとfinalを外す必要がある
			Field modifierField = null;
			modifierField = Field.class.getDeclaredField("modifiers");
			modifierField.setAccessible(true);
			modifierField.setInt(field, field.getModifiers() & ~Modifier.PRIVATE & ~Modifier.FINAL);
			field.setAccessible(true);
		}
		// fieldを更新する。
		//Object instance = reflectionService.getNewInstance();
		Object instance = memberService.getInstance();
		field.set(instance, reflectionService.validateArgument(reflectionService.getFieldArgument()));
		// ログ用にfield名と値を保持。
		this.fieldName = field.getName();
		this.fieldValue = field.get(instance);
		// アクセス制限を戻す
		field.setAccessible(false);
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		if (this.fieldName == null || this.fieldValue == null) {
			return "";
		}
		return "[Success] " + fieldName + " = " + fieldValue.toString() + ".\n";
	}
}
