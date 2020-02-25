package com.douzone.mysite.exception;

public class UserRepositoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserRepositoryException() {
		super("UserRepositoryException Occurs");
	}
	
	public UserRepositoryException(String Message) { //외부로부터 받은 것
		super(Message);
	}

	
}
