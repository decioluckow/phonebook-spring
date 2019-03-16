package com.phonebook.phonebookspring;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.phonebook.phonebookspring.exception.InvalidValueException;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@InjectMocks
	private ContactService service;
	
	@Mock
	private IContactDAO contactDAO;
	
	@Test
	public void testSaveContact_withEmptyData() throws InvalidValueException {
		expectedException.expect(InvalidValueException.class);
		expectedException.expectMessage("nome");
		final String name = "";
		final String phone = "";
		
		service.save(name, phone);
	}
	
	@Test
	public void testSaveContact_withValidData() throws InvalidValueException {
		final String name = "decio";
		final String phone = "9999-8888";
		
		service.save(name, phone);
		
		Mockito.verify(contactDAO, Mockito.times(1)).insert(Mockito.anyString(), Mockito.anyString());
	}
}
