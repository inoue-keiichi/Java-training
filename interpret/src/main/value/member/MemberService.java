package main.value.member;

import main.Service;

public class MemberService implements Service {
	private Object instance;
	private FieldPanel fieldPanel;
	private MethodPanel methodPanel;

	public Object getInstance() {
		return instance;
	}

	public FieldPanel getFieldPanel() {
		return fieldPanel;
	}

	public MethodPanel getMethodPanel() {
		return methodPanel;
	}

	public void setInstance(final Object instance) {
		this.instance = instance;
	}

	public void setFieldPanel(final FieldPanel fieldPanel) {
		this.fieldPanel = fieldPanel;
	}

	public void setMethodPanel(final MethodPanel methodPanel) {
		this.methodPanel = methodPanel;
	}
}
