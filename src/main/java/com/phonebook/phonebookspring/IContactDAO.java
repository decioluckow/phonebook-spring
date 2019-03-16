package com.phonebook.phonebookspring;

public interface IContactDAO {

	void insert(String name, String phone);

	void delete(String name);

}