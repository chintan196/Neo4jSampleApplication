package com.neo4jsampleapplication.employees.business;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Employee {

	@Id
	private final Integer id;

	private final String name;	
	
	public Employee(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
