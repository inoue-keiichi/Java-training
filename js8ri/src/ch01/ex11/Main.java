package ch01.ex11;

public class Main {

	public static void main(final String[] args) {
		System.out.println("Abstract And Abstract");
		final AbstractAndAbstractImpl abstractAndAbstractImpl = new AbstractAndAbstractImpl();
		abstractAndAbstractImpl.f();
		System.out.println("");

		System.out.println("Abstract And Default");
		final AbstractAndDefaultImpl abstractAndDefaultImpl = new AbstractAndDefaultImpl();
		abstractAndDefaultImpl.f();
		System.out.println("");

		System.out.println("Abstract And Static");
		final AbstractAndStaticImpl abstractAndStaticImpl = new AbstractAndStaticImpl();
		abstractAndStaticImpl.f();

		System.out.println("Default And Default");
		final DefaultAndDefaultImpl defaultAndDefaultImpl = new DefaultAndDefaultImpl();
		defaultAndDefaultImpl.f();
		System.out.println("");

		System.out.println("Default And Static");
		final DefaultAndStaticImpl defaultAndStaticImpl = new DefaultAndStaticImpl();
		defaultAndStaticImpl.f();
		System.out.println("");

		System.out.println("Static And Static");
		System.out.println("Static method is not extended.");
		System.out.println("");

		System.out.println("Super And Abstract");
		final SuperAndAbstractImpl superAndAbstractImpl = new SuperAndAbstractImpl();
		superAndAbstractImpl.f();
		System.out.println("");

		System.out.println("Super And Default");
		final SuperAndDefaultImpl superAndDefaultImpl = new SuperAndDefaultImpl();
		superAndDefaultImpl.f();
		System.out.println("");

		System.out.println("Super And Static");
		final SuperAndStatic superAndStatic = new SuperAndStatic();
		superAndStatic.f();
	}

}
