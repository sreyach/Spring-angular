package com.jnit.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

import com.jnit.app.model.Customer;
import com.jnit.app.repositories.CustomerRepository;

public class CustomerPersistenceTest extends ParentTest{

	@Autowired
	private CustomerRepository CustomerRepository;
	
	@Test
	public void testCustomerCreation(){
		Customer Customer = new Customer("sreya@gmail.com", "sreya", "reddy", "chennam", "sreya", LocalDate.of(1984, 9, 11), null);
		Customer createdCustomer = CustomerRepository.save(Customer);
		assertNotNull("Customer id not present",createdCustomer.getCustomerId());
	}

	@Test
	public void testFindCustomerById(){
		Customer Customer = CustomerRepository.findOne(1L);
		assertNotNull("Customer name not present",Customer.getCustomerName());
	}
	
	@Test
	public void testFindAll(){
		List<Customer>Customers = CustomerRepository.findAll();
		assertTrue( "Customers list is empty",Customers.size() > 0);
	}
	
	@Test
	public void testUpdateCustomer(){
		Customer Customer = CustomerRepository.findOne(1L);
		Customer.setmName("Reddy");
		Customer updatedCustomer = CustomerRepository.save(Customer);
		assertEquals("Reddy", updatedCustomer.getmName());
	}
	
	//@Test
	public void testDeleteCustomer(){
		Customer Customer = CustomerRepository.findOne(1L);
		CustomerRepository.delete(Customer);
		Customer CustomerObj = CustomerRepository.findOne(1L);
		assertNull("Customer seems to be not deleted",CustomerObj);
	}
	
	@Test
	public void testFindCustomerByCustomerName(){
		Optional<Customer> CustomerOptional = CustomerRepository.findByUserName("sreya@gmail.com");
		assertTrue("Customer not found with the Customer name",CustomerOptional.isPresent());
	}
	
	@Test
	public void testFindByLastName(){
		List<Customer>Customers = CustomerRepository.findByLName("B");
		assertTrue( "Customers list is empty",Customers.size() > 0);
	}

	@Test
	public void testCountByLastName(){
		Long count = CustomerRepository.countByLName("B");
		assertTrue( "Customers list is empty",count > 0);
	}
	
	@Test
	public void testFindByFNameAndLName(){
		Optional<Customer> CustomerOptional = CustomerRepository.findByFNameAndLName("sreya","reddy");
		assertTrue("Customer not found with the provided first and lname",CustomerOptional.isPresent());
	}

	@Test
	public void testFindByFNameOrLName(){
		List<Customer> Customers = CustomerRepository.findByFNameOrLName("sreya","reddy");
		assertTrue( "Customers list is empty",Customers.size() > 0);
	}
	
	@Test
	public void testFindDistinctByMName(){
		List<Customer> Customers = CustomerRepository.findDistinctByMName("Reddy");
		assertTrue( "Customers list is empty",Customers.size() > 0);	
	}
	
	@Test
	public void testFindFirst3ByLNameOrderByCustomerNameAsc(){
		List<Customer> Customers = CustomerRepository.findFirst3ByLNameOrderByUserNameAsc("reddy");
		assertTrue( "Customers list is empty",Customers.size() > 0);		
	}
	
	

}
