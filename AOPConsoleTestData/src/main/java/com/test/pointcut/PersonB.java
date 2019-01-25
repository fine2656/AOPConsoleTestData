package com.test.pointcut;

public class PersonB implements IQuiz {
	private String grade;
	
	@Override
	public String getGrade() {
		return grade;
	}
	@Override
	public void setGrade(String grade) {
		this.grade = grade;
	}


	@Override
	public void showPerson(int age, String name) {
		System.out.println("\n== 클래스 명 PersonB ==");
		System.out.println("1. AGE :"+age);
		System.out.println("2. NAME  :"+name);
	}

	@Override
	public int calc(int kor, int eng, int math) {
		int total = (int)(kor*0.2 + eng*0.3+math*0.5);
	
		return total;
	}
	@Override
	public int calc(int kor, int eng, int math, int java) {
		int total = (int)(kor*0.2 + eng*0.3+math*0.4+java*0.1);
		return total;
	}
////////////////////////////////////////////////////////////////
	private String passwd;

	public String getPasswd() {
		return passwd;
	}
	
	@Override
	public void setPasswd(String passwd) throws MyException {

		if(passwd == null ||( passwd != null &&  passwd.length()<10)) {
			throw new MyException(">> 암호의 길이는 10글자 이상이어야 합니다. <<");
		}
		else {
			this.passwd = passwd;	
		}	
	}
	


}
