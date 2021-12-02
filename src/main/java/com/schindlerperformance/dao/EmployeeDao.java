package com.schindlerperformance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.schindlerperformance.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{
	@Query("from Employee e where e.deletes=1")
	List<Employee> getEmployees();
	@Query("from Employee e where e.deletes=1 and e.name=?1")
	List<Employee> getEmployeeByName(String name);
	@Query("from Employee e where e.deletes=1 and e.name=?1")
	Optional<Employee> findByName(String name);
	
	

}
