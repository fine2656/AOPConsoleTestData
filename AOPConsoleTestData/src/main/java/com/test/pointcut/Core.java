package com.test.pointcut;

public class Core implements ICore {

	public int num = 100;
	private String name ="홍길동";
	
	public String getName() {
		return name;
	}
	@Override
	public void m1() {
		System.out.println("주업무 1");
		
	}
	@Override
	public void m2() {
		System.out.println("주업무 2");
		
	}
	@Override
	public void m3(String color, String subject) {

		System.out.println("주업무 3");
		System.out.print("색상 : "+color);
		System.out.println(" / 과목 : "+subject);
		
	}
	@Override
	public void m4() {
		System.out.println("주업무 4");
		
	}

}
