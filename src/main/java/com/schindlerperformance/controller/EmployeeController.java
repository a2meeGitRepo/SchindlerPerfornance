package com.schindlerperformance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schindlerperformance.model.Employee;
import com.schindlerperformance.model.Status;
import com.schindlerperformance.services.EmployeeServices;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeServices employeeServices;
	
	
@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	
	Status addEmployee(@RequestBody Employee employee) {
		try {
			employeeServices.addEmployee(employee);
			System.out.println("First Name:"+employee.getName());
			return new Status("Employee added Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Employee> getEmployees() {
		List<Employee> employeeList = null;
		try {
			employeeList = employeeServices.getEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}
	
@RequestMapping(value = "/delete/{employee_id}", method = RequestMethod.GET)
	public @ResponseBody
	Status deleteEmployee(@PathVariable("employee_id") int employee_id) {
		try {
			employeeServices.deleteEmployee(employee_id);
			return new Status("Employee Deleted Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}

}
