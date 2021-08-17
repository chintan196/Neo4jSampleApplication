package com.neo4jsampleapplication.employees.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neo4jsampleapplication.employees.business.Employee;
import com.neo4jsampleapplication.employees.business.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {

	private final EmployeeService employeeService;
	
	EmployeeController(EmployeeService employeeService)
	{
		this.employeeService = employeeService;
	}
		
	@GetMapping("/employee")
	public List<Employee> findByTitle(@RequestParam("q") String name) {
		return employeeService.searchEmployeesByName(name);
	}

	@PostMapping("/employee/id/name")
	public int AddEmployee(@PathVariable("id") Integer id, @PathVariable("name") String name) {
		return employeeService.AddNewEmployee(id, name);
	}
}
