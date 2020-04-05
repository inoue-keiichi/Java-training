package main.generator;

import main.di.AutowiredService;
import main.service.MemberService;

public class FieldPrintGenerator extends PrintGenerator {
	MemberService memberService;

	public FieldPrintGenerator(AutowiredService service) {
		super(service);
		this.memberService = service.memberService;
	}

	@Override
	public void execute() {
	}

	//TODO: delete it soon.
	public void execute(final Object instance) {
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		return "[Success] the fields of " + this.memberService.getInstance().getClass().getName()
				+ " were created.\n";
	}
}
