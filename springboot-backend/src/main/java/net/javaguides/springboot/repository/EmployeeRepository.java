package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepositoryImplementation<Employee, Long>{
	

}
