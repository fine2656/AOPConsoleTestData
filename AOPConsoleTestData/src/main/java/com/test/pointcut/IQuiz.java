package com.test.pointcut;

public interface IQuiz {
	
	void showPerson(int age,String name);
	
	int calc(int kor,int eng,int math);
	
	int calc(int kor,int eng,int math,int java);
	
	String getGrade();
	
	void setGrade(String grade);
	
	void setPasswd(String passwd) throws MyException;
	String getPasswd();
	
	
}
