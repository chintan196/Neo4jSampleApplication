package com.neo4jsampleapplication.employees.business;

import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	private final Neo4jClient neo4jClient;

	private final DatabaseSelectionProvider databaseSelectionProvider;
	
	EmployeeService(EmployeeRepository employeeRepository,
					Neo4jClient neo4jClient,
					DatabaseSelectionProvider databaseSelectionProvider)
	{
		this.employeeRepository = employeeRepository;
		this.neo4jClient = neo4jClient;
		this.databaseSelectionProvider = databaseSelectionProvider;
	}
	
	public Integer AddNewEmployee(Employee employee) {		
		return this.neo4jClient
				.query( "MERGE (e:Employee {id: $id, name:$name}) RETURN e.id" )
				.in( database() )
				.bindAll(Map.of("id", employee.getId()))
				.bindAll(Map.of("name", employee.getName()))
				.run()
				.counters()
				.propertiesSet();
	}

	public List<Employee> getAllEmployees() {
		return this.employeeRepository.getAllEmployees()
				.stream()
				.collect(Collectors.toList());
	}
	
	public List<Employee> searchEmployeesByName(String name) {
		return this.employeeRepository.findSearchResults(name)
				.stream()
				.collect(Collectors.toList());
	}

	private String database() {
		return databaseSelectionProvider.getDatabaseSelection().getValue();
	}
	
}
