package ch08.ex13;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TestCases.class)
public @interface TestCase {
	int expected();
}

@Retention(RetentionPolicy.RUNTIME)
@interface TestCases {
	TestCase[] value();
}
