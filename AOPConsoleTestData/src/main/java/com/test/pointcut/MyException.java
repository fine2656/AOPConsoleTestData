package com.test.pointcut;

public class MyException extends Exception {
	
		
	public MyException() {
		super("암호의 길이는 최소 8글자 이상이어야 합니다.");
	}
	public MyException(String errMsg) {
		super(errMsg);
	}
}
