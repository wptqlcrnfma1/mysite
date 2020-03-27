package com.douzone.mysite.exception;

public class BoardRepositoryException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public BoardRepositoryException() {
		super("GuestBookRepositoryException Occurs");
	}
	
	public BoardRepositoryException(String Message) {
		super(Message);
	}
}
