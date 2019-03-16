package com.phonebook.phonebookspring;

import com.phonebook.phonebookspring.exception.InvalidValueException;

public interface IContactService {
	
	void save(String name, String phone) throws InvalidValueException;
	
	void delete(String name) throws InvalidValueException;

}