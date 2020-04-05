package main.di;

import main.service.ConstructorService;
import main.service.InterpretViewService;
import main.service.MemberService;
import main.service.ReflectionService;

public class AutowiredService {
	public final ReflectionService reflectionService;
	public final ConstructorService constructorService;
	public final MemberService memberService;
	public final InterpretViewService interpretViewService;

	public AutowiredService() {
		reflectionService = new ReflectionService();
		constructorService = new ConstructorService();
		memberService = new MemberService();
		interpretViewService = new InterpretViewService();
	}
}
