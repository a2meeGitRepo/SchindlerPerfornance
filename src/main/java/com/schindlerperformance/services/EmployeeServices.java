package com.schindlerperformance.services;

import java.util.List;

import com.schindlerperformance.model.Employee;

public interface EmployeeServices {
	
	public void addEmployee(Employee employee);
	
	public List<Employee> getEmployees();
	
	public void deleteEmployee(int employee_id);

	public List<Employee> getEmployeeByName(String name);

	public Employee getEmployees(String name);

}
