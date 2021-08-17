package com.neo4jsampleapplication.employees.business;

import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface EmployeeRepository extends Repository<Employee, Integer> {

		@Query("MATCH (employee:Employee) WHERE employee.name CONTAINS $name RETURN employee")
		List<Employee> findSearchResults(@Param("name") String name);
		
		@Query("MATCH (employee:Employee) RETURN employee")
		List<Employee> getAllEmployees();
}