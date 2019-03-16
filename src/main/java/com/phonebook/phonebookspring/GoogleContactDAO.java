package com.phonebook.phonebookspring;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

import com.phonebook.phonebookspring.GoogleContactDAO.GoogleCondition;

@Component
@Conditional(value = GoogleCondition.class)
public class GoogleContactDAO implements IContactDAO {

	@Override
	public void insert(String name, String phone) {
		String url = "https://contacts.google.com/api/insert/" + name + "/" + phone + "/";
		System.out.println(url);
		//call url
	}

	@Override
	public void delete(String name) {
		String url = "https://contacts.google.com/api/delete/" + name + "/";
		System.out.println(url);
		//call url
	}

	static class GoogleCondition implements Condition {
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			String target = context.getEnvironment().getProperty("target");
			return target == null || "google".equals(target);
		}
	}
}
