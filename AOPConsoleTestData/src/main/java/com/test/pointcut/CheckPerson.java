package com.test.pointcut;

import javax.swing.GroupLayout.SequentialGroup;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CheckPerson {
	
/*	@Pointcut("execution(public void com.test.pointcut.PersonA.showPerson(int,String))")
	public void persona(){}
	
	
	@Before("persona()")
	public void before(JoinPoint joinPont) {
		int age = (Integer)joinPont.getArgs()[0];
		
		if(age <= 0) {
			System.out.println("PersonA 나이는 0세보다 커야합니다.");
		}
	}
	
	@Pointcut("execution(public void com.test.pointcut.PersonB.showPerson(int,String))")
	public void personb(){}
	
	
	@Before("personb()")
	public void before2(JoinPoint joinPont) {
		int age = (Integer)joinPont.getArgs()[0];		
		if(age <= 0) {
			System.out.println("\nPersonB 나이는 0세보다 커야합니다.");
		}
	}*/
	////////////////////////////////////////////////////////////////////////////////////////
	
	@Pointcut("execution(public void com.test.pointcut.Person*.showPerson(int,String))")
	public void personT(){}
	
	
	@Before("personT()")
	public void beforeT(JoinPoint joinPont) {
		int age = (Integer)joinPont.getArgs()[0];
		
		if(age <= 0) {
			System.out.print("-----------------------------");
			System.out.println("\nPerson 나이는 0세보다 커야합니다.");
		}
		/////////////////////////////////////////////////////
		String name = (String)joinPont.getArgs()[1];
		
		if(name == null || name.trim().isEmpty() || name.length()>10) {
			System.out.println("-----------------------------");
			System.out.println(name+"은 사용하실 수 없습니다. 공백이 아니나 10글자 이내여야 합니다.");
			return;
		}
	}
	
	
/*	
	@Pointcut("execution(public int com.test.pointcut.Person*.calc(int,int,int))")
	public void personCalc(){}

	
	@AfterReturning(pointcut="personCalc()",returning="total")
	public void personGrade(JoinPoint joinPoint,Object total) {
		IQuiz quiz =  (IQuiz)joinPoint.getTarget();	
	
		switch ((Integer)total/10) {
			case 10: 
			case 9:quiz.setGrade("A"); break;
			case 8:quiz.setGrade("B"); break;
			case 7:quiz.setGrade("C"); break;
			default: quiz.setGrade("D");
			break;
		}
	
	}
	
	*/
	@Pointcut("execution(public int com.test.pointcut.Person*.calc(..))")
	public void pc2(){}
	
	@AfterReturning(pointcut="pc2()",returning="total") //returning : return 받은 값
	public void afterreturning1(JoinPoint joinPoint,Object total) {
		//JoinPoint joinPoint는 앞에 위치해야한다.
		
		int jumsu = (Integer)total;
		IQuiz quiz = (IQuiz)joinPoint.getTarget(); // PersonA 와 PersonB의 공통인 Interface로 받아온다.
												 // getTarget은 해당 메소드 값을 전부 받아온다.
												 // 해당 되는 메소드 값을 interface에서 가져온다.
		switch (jumsu/10) {
			case 10: 
			case 9:quiz.setGrade("A"); break;
			case 8:quiz.setGrade("B"); break;
			case 7:quiz.setGrade("C"); break;
			default: quiz.setGrade("D");
			break;
		}	
		System.out.println("=== 등급 === : "+quiz.getGrade());
		//이미 set으로 이미 값을 저장해 주었기 때문에 바로 get으로 받아 올 수 있다.
	}
	
	@Pointcut("execution(public void com.test.pointcut.Person*.setPasswd(String))")
	public void pc3(){}
	
	@AfterThrowing(pointcut="pc3()",throwing="e")
	public void afterthorwing(MyException e) {
		System.out.println(e.getMessage());
		System.out.println("=> 다음으로 하고 싶은 공통의 작업을 기입하세요");
	}
	
	
	
}
