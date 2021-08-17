package com.neo4jsampleapplication.employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.neo4jsampleapplication.employees.business.Employee;
import com.neo4jsampleapplication.employees.business.EmployeeService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
class EmployeesApplicationTests {

	private static final String PASSWORD = "foobar";
	
    @Container
    private static final Neo4jContainer<?> neo4jContainer = new Neo4jContainer<>("neo4j:" + env("NEO4J_VERSION", "4.2"))
            .withAdminPassword(PASSWORD);
	
    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", neo4jContainer::getBoltUrl);
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", () -> PASSWORD);
        registry.add("spring.data.neo4j.database", () -> "neo4j");
    }
    
    
    @BeforeEach
    void setup(@Autowired Driver driver) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("MATCH (n) DETACH DELETE n");
                return null;
            });
        }
    }
    
    @Test
    public void add_and_search_employee_test(@Autowired EmployeeService service) {
    	Employee employee = new Employee(1,"Chintan");
    	int result = service.AddNewEmployee(employee);
    	assertThat(result).isGreaterThan(0);
    	
    	List<Employee> employees = service.getAllEmployees();
    	assertThat(employees).hasSize(1);
    	
    	employees = service.searchEmployeesByName("Chintan");
    	assertThat(employees).hasSize(1);
    	assertThat(employees.get(0).getName()).isEqualTo("Chintan");
    }
	
    private static String env(String name, String defaultValue) {
        String value = System.getenv(name);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }
}
