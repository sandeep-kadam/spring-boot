package com.sk.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sk.springboot.entity.Employee;

@Repository
public class EmployeeDAOImpl implements IEmployeeDAO {

	@Autowired
	private EntityManager entityManager;
	
	/*@Autowired
	public EmployeeDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}*/
	
	@Override
	public List<Employee> findAll() {
		
		List<Employee> employeeList = null;
		try {
			
			System.out.println("inside Hibernate findAll");
		// get the current hibernate session
		Session currentSession  = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);		

		// execute query and get result list
		employeeList = query.getResultList();
		
		}catch (Exception e) {
			System.out.println("Exception inside :: EmployeeDAO.findAll() : " + e);
		}
		// return the results
		return employeeList;
	}
	
	@Override
	public void save(Employee employee) {
		
		try {
			// get the current hibernate session
			Session currentSession  = entityManager.unwrap(Session.class);
			
			//insert if id==0 Or update if id!=0
			currentSession.saveOrUpdate(employee);
			currentSession.close();
			
		}catch (Exception e) {
			System.out.println("Exception inside :: EmployeeDAO.save() : " + e);
		}
	}

	@Override
	public Employee findById(int employeeId) {
	
		Employee employee = null;
		try {
			// get the current hibernate session
			Session currentSession  = entityManager.unwrap(Session.class);
			
			//get the employee 
			employee = currentSession.get(Employee.class, employeeId);
		
			//currentSession.close();
			
		}catch (Exception e) {
			System.out.println("Exception inside :: EmployeeDAO.save() : " + e);
		}
		
		return employee;
	}
	
	@Override
	public int checkEmployeeExistsOrNot(String email) {
		Employee employee = null;
		int employeeId = 0;
		try {
			// get the current hibernate session
			Session currentSession  = entityManager.unwrap(Session.class);
			
			//get the employee 
			Query<Employee> query = currentSession.createQuery("from Employee where email=:emailId", Employee.class);
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

	@Override
	public void deleteById(int employeeId) {
		try {
			// get the current hibernate session
			Session currentSession  = entityManager.unwrap(Session.class);

			/*Employee employee = currentSession.get(Employee.class, employeeId);
			currentSession.delete(employee);*/
			
			Query<Employee> query = currentSession.createQuery("delete from Employee where id=:employeeId",Employee.class);
			query.setParameter("employeeId", employeeId);
			query.executeUpdate();
			currentSession.close();
			
		}catch (Exception e) {
			System.out.println("Exception inside :: EmployeeDAO.save() : " + e);
		}
		
	}

}
