package ch08.ex13;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

@SupportedAnnotationTypes("*")
public class MyAnnotationProcessor extends AbstractProcessor {

	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
		final Messager messager = super.processingEnv.getMessager();

		messager.printMessage(Kind.OTHER, "Other");
		messager.printMessage(Kind.NOTE, "Note");
		messager.printMessage(Kind.WARNING, "Warning");
		messager.printMessage(Kind.MANDATORY_WARNING, "Mandatory Warning");
		messager.printMessage(Kind.ERROR, "Error");

		return true;
	}

}
