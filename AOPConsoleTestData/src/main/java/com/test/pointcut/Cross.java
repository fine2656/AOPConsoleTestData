package com.test.pointcut;

import java.util.HashMap;
import java.util.Set;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Cross {

	@Pointcut("execution(public void com.test.pointcut.Core.m1())")
	public void pc1() {}
	
	@Before("pc1()")
	public void before(JoinPoint joinPoint) {
		System.out.println("보조 업무");
		
		// 주업무 객체를 반환한다.(반환되는 지점)
		System.out.println(joinPoint.getTarget());
		//com.test.pointcut.Core@3a079870
		/*
		  getTarget() 은 주업무 객체(메소드)를 반환하기 때문에 이것을 사용하여 
		   주업무의 여러가지 정보를 얻어 올 수 있다.
		   이를 통해 보조업무에서 다양한 적용이 가능하다. 
		 */
		// 참고 Pointcut AspectJ 형식을 반환한다.
		System.out.println(joinPoint.toLongString());
		System.out.println(joinPoint.toShortString());
		System.out.println(joinPoint.toString());
		/*
		 execution(public abstract void com.test.pointcut.ICore.m1())
		 execution(ICore.m1())
		 execution(void com.test.pointcut.ICore.m1())
		*/
		
		//Pointcut 메소드 형식을 반환한다.
		System.out.println(joinPoint.getSignature());
		//void com.test.pointcut.ICore.m1()
		
		//인자값 리스트를 반환한다
		System.out.println(joinPoint.getArgs());
		//[Ljava.lang.Object;@3e3047e6
		//getArgs()는 Pointcut의 파라미터값을 반환한다.
		// 주업무 객체가 실행되기 위한 인자값에 대한 정보를 보조 업무 객체가 접근가능하다는 뜻이다

		
		
	}
	@Pointcut("execution(public void com.test.pointcut.Core.m2())")
	public void pc2() {}	
	
	@Before("pc2()") // pc2(m2) 를 하기 이전에
	public void before2(JoinPoint joinPoint) {
		
		// 주 업무 객체를 반환한다.
		Core core = (Core)joinPoint.getTarget();
		
		// 주업무 객체의 멤버를 접근한다.
		System.out.println(core.num); //100
		System.out.println(core.getName()); //홍길동
				
	}
	
	@Pointcut("execution(public void com.test.pointcut.Core.m3(String ,String))")
	public void pc3() {}
	HashMap<String, String> mapColor = new HashMap<String,String>();
	HashMap<String, String> mapSubject = new HashMap<String,String>();
	@After("pc3()") // 주업무가 끝난 다음에
	public void after(JoinPoint joinPoint) {
		String color = (String)joinPoint.getArgs()[0];//getArgs()[0] : 첫번재 파라미터를 읽어온다. getArgs()[1] : 두번째 파라미터를 읽어온다 .. getArgs()[n] : n+1 번째 파라미터를 읽어온다.
		// MainTest에서 core.m3(String color, String subject) 를 호출할 때 
		// m3 첫번째 파라미터인 String color을 반환해오는 것이다.
		if(color != null && !color.trim().isEmpty()) {
			if(color.equalsIgnoreCase("red")) mapColor.put(color, "빨강");
			else if(color.equalsIgnoreCase("blue")) mapColor.put(color, "파랑");
			else if(color.equalsIgnoreCase("yellow")) mapColor.put(color, "노랑");
			else if(color.equalsIgnoreCase("green")) mapColor.put(color, "초록");
			else mapColor.put(color, "기타");
		}else {
			color = "none";
			mapColor.put(color, "색없음");
		}
		Set<String> keyset = mapColor.keySet();
		for(String key:keyset) {
			System.out.println(key+"="+mapColor.get(key));
		}
		////////////////////////////////////////////
		System.out.println("-------------------------------------------------------");
		String subject = (String)joinPoint.getArgs()[1];
		if(subject != null && !subject.trim().isEmpty()) {
			if(subject.equalsIgnoreCase("kor")) mapSubject.put(subject,"국어");
			else if(subject.equalsIgnoreCase("eng")) mapSubject.put(subject,"영어");
			else if(subject.equalsIgnoreCase("math")) mapSubject.put(subject,"수학");
			else if(subject.equalsIgnoreCase("science")) mapSubject.put(subject,"과학");
			else if(subject.equalsIgnoreCase("society")) mapSubject.put(subject,"사회");					
			else {
				subject = "none";
				mapSubject.put(subject, "과목없음");
			}
			
			Set<String> keyset2 = mapSubject.keySet();
			for(String key :keyset2) {
				System.out.println(key+"="+mapSubject.get(key));
			}
		}
		System.out.println("-------------------------------------------------------");
			
	}
	



//////===== !!!!! 정리 !!!!! ===== //////
/*
   주업무 객체에서 보조업무 객체로 무언가를 전달하려면......
1. 주업무 객체의 멤버값  (public 으로 전달)
  --> JoinPoint 의   getTarget() 사용
  
2. 주업무 객체의 Pointcut의 인자값
  --> JoinPoint 의  getArgs()[0] 사용
  
3. 주업무 객체의 Pointcut의 결과값 
  --> After returning Advice 사용    
*/

/*
=== Pointcut 명시자 대신에 within 명시자를 사용해서 선언하기 ===
	- within 명시자는 특정 타입에 속하는 메소드를 Pointcut으로 설정한다.
	- 타입 패턴만을 이용해서 Pointcut을 설정한다.
	- within 명시자는 execution 명시자에 비해 세밀도가 떨어진다.
	
	ex) @Pointcut("within(com.test.pointcut.Core)")
		- com.test.pointcut.Core 클래스의 모든 메소드를 Pointcut으로 선언한다.
	ex) @Pointcut("within(Core)")
		- 위의 예의 패키지 생략 표현
	ex) @Pointcut("within(com.test.pointcut.*)")
		- com.test.pointcut 패키지내의 모든 클래스의 모든 메소드를 Pointcut으로 선언한다.
	ex) @Pointcut("within(com.test...*)")
		- com.test와 그 하위 패키지내의 모든 클래스의 모든 메소드를 Pointcut으로 선언한다.
*/

	@Pointcut("within(com.test.pointcut.Core)")
	public void pc4() { }
	
	@Before("pc4()")
	public void before4() {
		System.out.println(">>>>보조업무4<<<<");
	}


/*	
=== Pointcut 명시자 대신에 bean 명시자를 사용해서 선언하기 ===
	- baen 명시자는 빈 이름의 패턴을 사용해서 Pointcut을 설정한다.
	- Spring에서만 사용 가능한 명시자이다. (AspectJ에는 없다.)
	
	ex) @Pointcut("bean(core)")
		- 빈 이름이 "core"인 클래스의 모든 메소드를 Pointcut으로 선언한다.
	ex) @Pointcut("bean(c*)")
		- 빈 이름이 "c"로 시작하는 모든 클래스의 모든 메소드를 Pointcut으로 선언한다.
	ex) @Pointcut("bean(*c)")
		- 빈 이름이 "c"로 끝나는 모든 클래스의 모든 메소드를 Pointcut으로 선언한다.

*/

	@Pointcut("bean(core)")
	public void pc5() { }
	
	@Before("pc5()")
	public void before5() {
		System.out.println("====보조업무5====");
	}
}