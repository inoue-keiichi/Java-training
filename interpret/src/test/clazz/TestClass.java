package test.clazz;

public class TestClass {
	public int publicIntValue = 0;
	public boolean publicBooleanValue = false;
	public static int publicStaticIntValue = 0;
	private int privateIntValue = 0;
	private boolean privateBooleanValue = false;
	private final int privateFinalIntValue = 0;
	private final boolean privateFinalBooleanValue = false;
	private static final int privateStaticFinalInt = 0;
	private static final boolean privateStaticFinalBoolean = false;

	public TestClass() {

	}

	public TestClass(final int i, final boolean b) {

	}

	public int publicInt(final int num) {
		return num * 10;
	}

	private int privateInt(final int num) {
		return num * 10;
	}

	public static int publicStaticInt(final int num) {
		return num * 10;
	}

	private static int privateStaticInt(final int num) {
		return num * 10;
	}
	
	public void publicIntAndBoolean(final int num, final boolean bol) {
		
	}

}
