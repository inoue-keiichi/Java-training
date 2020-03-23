package main;

import main.array.member.SetterPanel;
import main.value.ReflectionService;
import main.value.member.ConstructorCreatePrintGenerator;
import main.value.member.ConstructorPanel;
import main.value.member.ConstructorPrintGenerator;
import main.value.member.FieldPanel;
import main.value.member.FieldPrintGenerator;
import main.value.member.FieldUpdatePrintGenerator;
import main.value.member.MemberPanel;
import main.value.member.MethodExecutePrintGenerator;
import main.value.member.MethodPanel;
import main.value.member.MethodPrintGenerator;
import main.value.member.ObjectMemberTab;
import main.value.type.ObjectTypeInputPanel;

public class Autowired {
	// Panel
	public static final ObjectMemberTab objectMemberTab = new ObjectMemberTab();
	public static final MethodPanel methodPanel = new MethodPanel();
	public static final FieldPanel fieldPanel = new FieldPanel();
	public static final ConstructorPanel constructorPanel = new ConstructorPanel();
	public static final SetterPanel setterPanel = new SetterPanel();
	public static final ObjectTypeInputPanel objectTypeInputPanel = new ObjectTypeInputPanel();
	public static final MemberPanel memberPanel = new MemberPanel();

	// Generator
	public static final FieldPrintGenerator fieldPrintGenerator = new FieldPrintGenerator();
	public static final FieldUpdatePrintGenerator fieldUpdatePrintGenerator = new FieldUpdatePrintGenerator();
	public static final MethodPrintGenerator methodPrintGenerator = new MethodPrintGenerator();
	public static final MethodExecutePrintGenerator methodExecutePrintGenerator = new MethodExecutePrintGenerator();
	public static final ConstructorCreatePrintGenerator constructorCreatePrintGenerator = new ConstructorCreatePrintGenerator();
	public static final ConstructorPrintGenerator constructorPrintGenerator = new ConstructorPrintGenerator();
	public static final InstanceField instanceField = new InstanceField();

	public static final ReflectionService reflectionService = new ReflectionService();
}
