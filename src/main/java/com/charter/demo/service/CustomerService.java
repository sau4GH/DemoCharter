package com.charter.demo.service;

import java.util.List;
import java.util.Optional;

import com.charter.demo.entity.Customer;

public interface CustomerService {
	
	public List<Customer> fetchAllCustomers();
	public Optional<Customer> findCustomerById(Integer custId);
	public Customer addNewCustomer(Customer customer);
	public void removeCustomer(Integer custId);

}
