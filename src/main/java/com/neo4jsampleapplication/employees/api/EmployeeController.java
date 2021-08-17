package com.neo4jsampleapplication.employees.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neo4jsampleapplication.employees.business.Employee;
import com.neo4jsampleapplication.employees.business.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "EmployeeController", description = "REST APIs related to Employee Entity.")
@RestController
public class EmployeeController {

	private final EmployeeService employeeService;
	
	EmployeeController(EmployeeService employeeService)
	{
		this.employeeService = employeeService;
	}

	@ApiOperation(value = "Get list of all the Employees. For managing load, this method will limit the results to 100 employees a time.",
			response = Iterable.class, tags = "getAll")
	@GetMapping("/employee/all")
	public List<Employee> getAll() {
		return employeeService.getAllEmployees();
	}
	
	@ApiOperation(value = "Find employee(s) by name.", response = Iterable.class, tags = "findByName")
	@GetMapping("/employee")
	public List<Employee> findByName(@RequestParam("name") String name) {
		return employeeService.searchEmployeesByName(name);
	}

	@ApiOperation(value = "Add a new employee", response = Iterable.class, tags = "addEmployee")
	@PostMapping("/employee")
	public int addEmployee(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		return employeeService.AddNewEmployee(id, name);
	}
}
