package com.jnit.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//Relly -
import com.jnit.app.model.Customer;
//findBy, readBy, queryBy, countBy, and getBy.
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	public Optional<Customer> findByUserName(String UserName);
	
	public List<Customer>findByLName(String lastName);

	public Long countByLName(String lastName);

	public Optional<Customer>findByFNameAndLName(String firstName, String lastName);

	public List<Customer>findByFNameOrLName(String firstName, String lastName);
	
	public List<Customer>findDistinctByMName(String middleName);

	public List<Customer> findFirst3ByLNameOrderByUserNameAsc(String lastName);

}
