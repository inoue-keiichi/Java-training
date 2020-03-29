package main;

import main.array.member.SetterPrintGenerator;
import main.array.type.ArrayCreatePrintGenerator;
import main.value.ReflectionService;
import main.value.member.ConstructorCreatePrintGenerator;
import main.value.member.ConstructorPrintGenerator;
import main.value.member.ConstructorService;
import main.value.member.FieldPrintGenerator;
import main.value.member.FieldUpdatePrintGenerator;
import main.value.member.MemberService;
import main.value.member.MethodExecutePrintGenerator;
import main.value.member.MethodPrintGenerator;

public class Autowired {
	// Generator
	public static FieldPrintGenerator fieldPrintGenerator;
	public static FieldUpdatePrintGenerator fieldUpdatePrintGenerator;
	public static MethodPrintGenerator methodPrintGenerator;
	public static MethodExecutePrintGenerator methodExecutePrintGenerator;
	public static ConstructorCreatePrintGenerator constructorCreatePrintGenerator;
	public static ArrayCreatePrintGenerator arrayCreatePrintGenerator;
	public static ConstructorPrintGenerator constructorPrintGenerator;
	public static SetterPrintGenerator setterPrintGenerator;
	//Other
	public static ErrorHandler errorHandler;
	//Service
	public static ReflectionService reflectionService;
	public static ConstructorService constructorService;
	public static MemberService memberService;

	public Autowired() {
		//Service
		reflectionService = new ReflectionService();
		constructorService = new ConstructorService();
		memberService = new MemberService();

		// Generator
		fieldPrintGenerator = new FieldPrintGenerator();
		fieldUpdatePrintGenerator = new FieldUpdatePrintGenerator();
		methodPrintGenerator = new MethodPrintGenerator();
		methodExecutePrintGenerator = new MethodExecutePrintGenerator();
		constructorCreatePrintGenerator = new ConstructorCreatePrintGenerator();
		arrayCreatePrintGenerator = new ArrayCreatePrintGenerator();
		constructorPrintGenerator = new ConstructorPrintGenerator();
		setterPrintGenerator = new SetterPrintGenerator();

		//Other
		errorHandler = new ErrorHandler();
	}
}
