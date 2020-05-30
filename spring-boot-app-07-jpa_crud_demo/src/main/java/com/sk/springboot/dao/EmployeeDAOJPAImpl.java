package com.sk.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sk.springboot.entity.Employee;

//@Repository used to include this dao class in component scan as a bean
@Repository
public class EmployeeDAOJPAImpl implements IEmployeeDAO{

	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findAll() {
		
		List<Employee> employees = null;
		try {
			System.out.println("inside JPA findAll");
			Query query = entityManager.createQuery("from Employee",Employee.class);
			
			employees = query.getResultList();
			
		}catch (Exception e) {
			System.out.println("Exception inside : EmployeeDAOJPAImpl.findAll() # "+e);
		}
		
		return employees;
	}

	@Override
	public void save(Employee employee) {
		try {
			Employee dbEmpObj = entityManager.merge(employee);
			employee.setId(dbEmpObj.getId());
		}catch (Exception e) {
			System.out.println("Exception inside : EmployeeDAOJPAImpl.findById() # "+e);
		}
	}

	@Override
	public Employee findById(int employeeId) {
		Employee employee = null;
		
		try {
			employee = entityManager.find(Employee.class, employeeId);
		}catch (Exception e) {
			System.out.println("Exception inside : EmployeeDAOJPAImpl.findById() # "+e);
		}
		return employee;
	}

	@Override
	public void deleteById(int employeeId) {
		try {
			Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
			query.setParameter("employeeId", employeeId);
			
			query.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("Exception inside : EmployeeDAOJPAImpl.findById() # "+e);
		}
	}

	@Override
	public int checkEmployeeExistsOrNot(String email) {
		Employee employee = null;
		int employeeId = 0;
		try {
			// get the current hibernate session
			Session currentSession  = entityManager.unwrap(Session.class);
			
			//get the employee 
			Query query = entityManager.createQuery("from Employee where email=:emailId");
			query.setParameter("emailId", email);
			System.out.println("No. of Results : "+query.getResultList().size());
			if(query.getResultList().size() == 1) {
				employee = (Employee) query.getSingleResult();
				employeeId = employee.getId();
			} else {
				employeeId = 0;
			}
			
			System.out.println("EmployeeId ## "+employeeId);
			currentSession.close();
			
		}catch (Exception e) {
			
			
			
			System.out.println("Exception inside :: EmployeeDAO.checkEmployeeExistsOrNot() : " + e);
		}
		
		return employeeId;
	}

}
