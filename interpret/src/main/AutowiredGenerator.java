package main;

import main.array.member.SetterClearPrintGenerator;
import main.array.member.SetterPrintGenerator;
import main.array.type.ArrayCreatePrintGenerator;
import main.value.member.ConstructorClearPrintGenerator;
import main.value.member.ConstructorCreatePrintGenerator;
import main.value.member.ConstructorPrintGenerator;
import main.value.member.FieldPrintGenerator;
import main.value.member.FieldUpdatePrintGenerator;
import main.value.member.MethodExecutePrintGenerator;
import main.value.member.MethodPrintGenerator;

public class AutowiredGenerator {
	// Generator
	public final FieldPrintGenerator fieldPrintGenerator;
	public final FieldUpdatePrintGenerator fieldUpdatePrintGenerator;
	public final MethodPrintGenerator methodPrintGenerator;
	public final MethodExecutePrintGenerator methodExecutePrintGenerator;
	public final ConstructorCreatePrintGenerator constructorCreatePrintGenerator;
	public final ConstructorClearPrintGenerator constructorClearPrintGenerator;
	public final ArrayCreatePrintGenerator arrayCreatePrintGenerator;
	public final ConstructorPrintGenerator constructorPrintGenerator;
	public final SetterPrintGenerator setterPrintGenerator;
	public final SetterClearPrintGenerator setterClearPrintGenerator;
	public final ErrorHandler errorHandler;

	public AutowiredGenerator(final AutowiredService service) {
		fieldPrintGenerator = new FieldPrintGenerator(service);
		fieldUpdatePrintGenerator = new FieldUpdatePrintGenerator(service);
		methodPrintGenerator = new MethodPrintGenerator(service);
		methodExecutePrintGenerator = new MethodExecutePrintGenerator(service);
		constructorCreatePrintGenerator = new ConstructorCreatePrintGenerator(service);
		constructorClearPrintGenerator = new ConstructorClearPrintGenerator(service);
		arrayCreatePrintGenerator = new ArrayCreatePrintGenerator(service);
		constructorPrintGenerator = new ConstructorPrintGenerator(service);
		setterPrintGenerator = new SetterPrintGenerator(service);
		setterClearPrintGenerator = new SetterClearPrintGenerator(service);
		errorHandler = new ErrorHandler(service);
	}
}
