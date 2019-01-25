package com.test.pointcut;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainTest {
	public static void main(String[] args) {
		// IoC(Inversion of Control)컨테이너(Spring컨테이너)로 사용되는 
		// ApplicationContext 객체 생성하기 
		// 이것은 XML 설정을 로드함으로써 생성할 수 있다. 
		ApplicationContext context = new GenericXmlApplicationContext("classpath:main.xml"); 
	
		// 빈(bean) 객체 생성하기
		ICore core = context.getBean("core", ICore.class); 
		// core.xml 파일에서 id 가 "core"로 되어진 객체를 얻어옴.	
		
		core.m1();
		System.out.println("-----------------------------------------------");
		core.m2();
		System.out.println("===============================================");
		core.m3("red", "kor");
		core.m3("blue", "eng");
		core.m3("red", "math");		
		core.m3(" ", "science");
		core.m3("yellow", "society");
		
		/////////////////퀴즈//////////////////////////
		System.out.println("\n=====퀴즈=====\n");
		// 빈(bean) 객체 생성하기
		IQuiz persona = context.getBean("persona", IQuiz.class); 
		IQuiz personb = context.getBean("personb", IQuiz.class); 
		// main.xml 파일에서 id 가 "personb"로 되어진 객체를 얻어옴.
		
		persona.showPerson(0, "이순신");
		personb.showPerson(-10, "일이삼사오육칠팔구십일");
		
		persona.calc(90, 80, 70);
		System.out.println(persona.getGrade());
		
		personb.calc(80, 50, 70,80);
		System.out.println(personb.getGrade());
		
		System.out.println("//////////////////////////////////////////////");
			
			
		try {
				persona.setPasswd("a12345");
		} catch (MyException e) {}	
		try {
					personb.setPasswd("a1234567");
		} catch (MyException e) {}

	}
}
