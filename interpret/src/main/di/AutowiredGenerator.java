package main.di;

import main.generator.ArrayCreatePrintGenerator;
import main.generator.ConstructorClearPrintGenerator;
import main.generator.ConstructorCreatePrintGenerator;
import main.generator.ConstructorPrintGenerator;
import main.generator.ErrorHandler;
import main.generator.FieldPrintGenerator;
import main.generator.FieldUpdatePrintGenerator;
import main.generator.MethodExecutePrintGenerator;
import main.generator.MethodPrintGenerator;
import main.generator.SetterClearPrintGenerator;
import main.generator.SetterPrintGenerator;

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
