package ch16.ex05;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

public class ReflectionAnnotation {
	@Target(METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface MethodAnnotation {

	}

	@Target(CONSTRUCTOR)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface ConstructorAnnotation {

	}

	@Target(FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface FieldAnnotation {

	}
}
