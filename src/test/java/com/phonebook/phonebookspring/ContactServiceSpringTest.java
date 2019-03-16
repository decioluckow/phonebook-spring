package com.phonebook.phonebookspring;

import static org.mockito.Mockito.never;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.phonebook.phonebookspring.exception.InvalidValueException;

@RunWith(SpringRunner.class)
public class ContactServiceSpringTest {
	
	@TestConfiguration
	static class ContactServiceImplTestContextConfiguration {
		
		@Bean
		public ContactService contactService() {
			return new ContactService();
		}
	}
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Autowired(required=true)
	private ContactService service;
	
	@MockBean
	private IContactDAO contactDAO;
	
	@Test
	public void testSaveContact_withValidData() throws InvalidValueException {
		service.save("decio", "9999-8888");
		Mockito.verify(contactDAO).insert(Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void testSaveContact_withNullNameAndValidPhone() throws InvalidValueException {
		expectedException.expect(InvalidValueException.class);
		expectedException.expectMessage("nome");
		service.save(null, "x");
		Mockito.verify(contactDAO, never()).insert(Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void testSaveContact_withInvalidNameAndValidPhone() throws InvalidValueException {
		expectedException.expect(InvalidValueException.class);
		expectedException.expectMessage("nome");
		service.save("", "x");
		Mockito.verify(contactDAO, Mockito.never()).insert(Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void testSaveContact_withInvalidNameAndNullPhone() throws InvalidValueException {
		expectedException.expect(InvalidValueException.class);
		expectedException.expectMessage("nome");
		service.save("", null);
		Mockito.verify(contactDAO, Mockito.never()).insert(Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void testSaveContact_withValidNameAndInvalidPhone() throws InvalidValueException {
		expectedException.expect(InvalidValueException.class);
		expectedException.expectMessage("fone");
		service.save("decio", "999");
		Mockito.verify(contactDAO, Mockito.never()).insert(Mockito.anyString(), Mockito.anyString());
	}

}
