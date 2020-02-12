package main;

public class MemberPrintGenerator extends PrintGenerator {
	private static final MemberPrintGenerator memberPrintGenerator = new MemberPrintGenerator();

	private MemberPrintGenerator() {

	}

	@Override
	public void execute() throws Throwable {
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public static MemberPrintGenerator getInstance() {
		return memberPrintGenerator;
	}

}
