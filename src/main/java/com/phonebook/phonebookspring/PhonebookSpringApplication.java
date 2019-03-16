package com.phonebook.phonebookspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.phonebook.phonebookspring.exception.InvalidValueException;

@SpringBootApplication
public class PhonebookSpringApplication implements CommandLineRunner {

	@Autowired
	private ContactService contactService;

	public static void main(String[] args) {
		SpringApplication.run(PhonebookSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (!validateParams(args))
			showHelp();
		else {
			try {
				executeOperation(args);
			} catch (InvalidValueException e) {
				System.out.println("Campo " + e.getMessage() + "é inválido");
			}
		}
	}

	private boolean validateParams(String[] args) {
		return args.length >= 1
				&& ((args[0].equals("save") && args.length == 3) || (args[0].equals("delete") && args.length == 2));
	}

	private void showHelp() {
		System.out.println("Comando inválido, use: PhoneBook save nome telefone ou PhoneBook delete nome");
	}

	private void executeOperation(String[] args) throws InvalidValueException {
		String operation = args[0];
		if (operation.equals("save"))
			contactService.save(args[1], args[2]);
		else if (operation.equals("delete"))
			contactService.delete(args[1]);
		else
			showHelp();
	}
}
