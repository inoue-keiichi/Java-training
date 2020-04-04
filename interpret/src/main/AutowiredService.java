package main;

import main.value.ReflectionService;
import main.value.member.ConstructorService;
import main.value.member.MemberService;

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
