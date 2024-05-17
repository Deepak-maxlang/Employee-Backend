package net.javaguides.springboot.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")//reactPortlink
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	private static final Logger logger = LogManager.getLogger(EmployeeController.class);
	
    @Autowired
 	private EmployeeRepository employeeRepository;
    
    //get all employees;
    
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
    	return employeeRepository.findAll();
    }

    
  //create employee rest API
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        //TODO: process POST request
    	
        return employeeRepository.save(employee);
    }
    
    //getEmployeebyId  Rest API
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee>  getEmployeeById(@PathVariable Long id) {
    	Employee employee = employeeRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: "+id));
    	return ResponseEntity.ok(employee);
    	
    	
    }
    
    //updateEmployee rest API
    @PutMapping("/employees/{id}")
   
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
       
        
    	Employee employee = employeeRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: "+id));
    	
    	
    	employee.setFirstName(employeeDetails.getFirstName());
    	employee.setLastName(employeeDetails.getLastName());
    	employee.setEmailId(employeeDetails.getEmailId());
    	
    	Employee updatedEmployee =  employeeRepository.save(employee);
    	return ResponseEntity.ok(updatedEmployee);
    } 
    
    
    
    //build Delete Employee REST API
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id){
    	
    	Employee employee = employeeRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: "+id));
    	
    	employeeRepository.delete(employee);
    	
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	
    }
    
    
    
}