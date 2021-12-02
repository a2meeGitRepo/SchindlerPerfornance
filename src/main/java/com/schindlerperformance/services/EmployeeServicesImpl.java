package com.schindlerperformance.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schindlerperformance.dao.EmployeeDao;
import com.schindlerperformance.model.Employee;
@Service
public class EmployeeServicesImpl implements EmployeeServices{
	
	@Autowired
	EmployeeDao employeeDao;
	
	
	
	public void addEmployee(Employee employee){
		 employeeDao.save(employee);	
	}
	
	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return employeeDao.getEmployees();
	}

	@Override
	public void deleteEmployee(int employee_id) {
		// TODO Auto-generated method stub
		Optional<Employee>  optional= employeeDao.findById(employee_id);
		if(optional.isPresent()){
			employeeDao.delete(optional.get());
		}
	}


	@Override
	public List<Employee> getEmployeeByName(String name) {
		return employeeDao.getEmployeeByName(name);
		
	}

	@Override
	public Employee getEmployees(String name) {
		Optional<Employee> optional = employeeDao.findByName(name);
		return optional.isPresent()?optional.get():null;
	}

}
