package com.phonebook.phonebookspring;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

import com.phonebook.phonebookspring.DatabaseContactDAO.DatabaseCondition;

@Component
@Conditional(value = DatabaseCondition.class)
public class DatabaseContactDAO implements IContactDAO {

	@Override
	public void insert(String name, String phone) {
		String sql = "insert into contact (name, phone) values ('" + name + "','" + phone + "')";
		System.out.println(sql);
		//execute sql
	}

	@Override
	public void delete(String name) {
		String sql = "delete from contact where name = '"+ name +"'";
		System.out.println(sql);
		//execute sql
	}
	
	static class DatabaseCondition implements Condition {
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			String target = context.getEnvironment().getProperty("target");
			return "database".equals(target);
		}
	}
}
