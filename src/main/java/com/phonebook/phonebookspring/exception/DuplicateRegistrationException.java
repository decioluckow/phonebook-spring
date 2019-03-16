package com.phonebook.phonebookspring.exception;

public class DuplicateRegistrationException extends Exception {
	
	private static final long serialVersionUID = 7715563286899235815L;

	public DuplicateRegistrationException(String message) {
		super(message);
	}

}
