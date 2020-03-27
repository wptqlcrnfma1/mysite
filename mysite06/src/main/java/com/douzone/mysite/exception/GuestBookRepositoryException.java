package com.douzone.mysite.exception;

public class GuestBookRepositoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public GuestBookRepositoryException() {
		super("GuestBookRepositoryException Occurs");
	}
	
	public GuestBookRepositoryException(String Message) {
		super(Message);
	}
	
}
