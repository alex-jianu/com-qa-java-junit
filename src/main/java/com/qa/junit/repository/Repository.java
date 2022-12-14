package com.qa.junit.repository;

import java.util.Arrays;
import java.util.List;

import com.qa.junit.exception.EmployeeAlreadyExistsException;
import com.qa.junit.exception.EmployeeNotFoundException;
import com.qa.junit.exception.InvalidInputException;
import com.qa.junit.model.Employee;

public class Repository {

	// code to perform db operations

	List<Employee> empList;

	public Repository() {
		this.empList = Arrays.asList(new Employee(111, "emp1", 32423.23), new Employee(222, "emp2", 42423.23),
				new Employee(333, "emp3", 52423.23));

	}

	public Employee getEmployeeById(int id) throws EmployeeNotFoundException, InvalidInputException  {
		boolean valid = validId(id);
		if(!valid)
			throw new InvalidInputException("Id should be positive");
		return this.empList.stream().filter(emp -> emp.getId() == id).findFirst().orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found with this Id"));
				
	}

	private boolean validId(int id) {
		boolean valid = false;
		if(id > 0)
			valid = true;
		return valid;
	}
	
	public List<Employee> getAllEmployees(){
		return this.empList;
	}

	public void addEmployee(Employee employee) throws EmployeeAlreadyExistsException, InvalidInputException{
		for (int i=0; i<empList.size(); i++) {
			if (empList.get(i).getId() == employee.getId() || empList.get(i).getName() == employee.getName()) {
				throw new EmployeeAlreadyExistsException("This employee already exists");
			}
			if (employee.getClass() != Employee.class || employee == null) {
				throw new InvalidInputException("That is not a valid input for a new employee");
			}
		}
		this.empList.add(employee);
		System.out.println("Employee "+employee.toString()+" was successfully added!");
	}
}
