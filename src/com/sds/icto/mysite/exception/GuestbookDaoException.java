package com.sds.icto.mysite.exception;

public class GuestbookDaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GuestbookDaoException(){
		super("Guestbook Dao Exception");
	}
	
	public GuestbookDaoException(String msg){
		super(msg);
	}
}
