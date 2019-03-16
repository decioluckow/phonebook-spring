package com.phonebook.phonebookspring;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phonebook.phonebookspring.exception.InvalidValueException;

/**
 * Responsavel por garantir a qualidade das informações pra envio para o dao
 */
@Service
public class ContactService {
	
	@Autowired
	private IContactDAO dao;
	
	private Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{4,5}-[0-9]{4}$");
	private Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z]{1,}$");

	public void save(String name, String phone) throws InvalidValueException {
		verifyName(name);
		verifyPhone(phone);
		dao.insert(name, phone);
	}
	
	public void delete(String name) throws InvalidValueException {
		verifyName(name);
		dao.delete(name);
	}

	private void verifyName(String name) throws InvalidValueException {
		if (name == null || !NAME_PATTERN.matcher(name).matches()) {
			throw new InvalidValueException("nome");
		}
	}

	private void verifyPhone(String phone) throws InvalidValueException {
		if (phone == null || !PHONE_PATTERN.matcher(phone).matches()) {
			throw new InvalidValueException("fone");
		}
	}
}
