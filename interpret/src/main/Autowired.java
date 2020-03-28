package main;

import main.array.member.SetterPrintGenerator;
import main.array.type.ArrayCreatePrintGenerator;
import main.value.ReflectionService;
import main.value.member.ConstructorCreatePrintGenerator;
import main.value.member.ConstructorPanel;
import main.value.member.ConstructorPrintGenerator;
import main.value.member.FieldPanel;
import main.value.member.FieldPrintGenerator;
import main.value.member.FieldUpdatePrintGenerator;
import main.value.member.MethodExecutePrintGenerator;
import main.value.member.MethodPanel;
import main.value.member.MethodPrintGenerator;

public class Autowired {
	// Panel
	public static MethodPanel methodPanel;
	public static FieldPanel fieldPanel;
	public static ConstructorPanel constructorPanel;

	// Generator
	public static FieldPrintGenerator fieldPrintGenerator;
	public static FieldUpdatePrintGenerator fieldUpdatePrintGenerator;
	public static MethodPrintGenerator methodPrintGenerator;
	public static MethodExecutePrintGenerator methodExecutePrintGenerator;
	public static ConstructorCreatePrintGenerator constructorCreatePrintGenerator;
	public static ArrayCreatePrintGenerator arrayCreatePrintGenerator;
	public static ConstructorPrintGenerator constructorPrintGenerator;
	//public static InstanceField instanceField;
	public static SetterPrintGenerator setterPrintGenerator;

	//Other
	public static ErrorHandler errorHandler;
	//Service
	public static ReflectionService reflectionService;
	//public static ConstructorService constructorService;

	public Autowired() {
		//Service
		reflectionService = new ReflectionService();
		//constructorService = new ConstructorService();

		// Panel
		methodPanel = new MethodPanel();
		fieldPanel = new FieldPanel();
		constructorPanel = new ConstructorPanel();

		// Generator
		fieldPrintGenerator = new FieldPrintGenerator();
		fieldUpdatePrintGenerator = new FieldUpdatePrintGenerator();
		methodPrintGenerator = new MethodPrintGenerator();
		methodExecutePrintGenerator = new MethodExecutePrintGenerator();
		constructorCreatePrintGenerator = new ConstructorCreatePrintGenerator();
		arrayCreatePrintGenerator = new ArrayCreatePrintGenerator();
		constructorPrintGenerator = new ConstructorPrintGenerator();
		//instanceField = new InstanceField();
		setterPrintGenerator = new SetterPrintGenerator();

		//Other
		errorHandler = new ErrorHandler();
	}
}
