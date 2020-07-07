package com.charter.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charter.demo.dao.CustomerRepo;
import com.charter.demo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public List<Customer> fetchAllCustomers() {
		List<Customer> list = customerRepo.findAll();
		
		return list;
	}

	@Override
	public Customer addNewCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public void removeCustomer(Integer custId) {
		customerRepo.deleteById(custId);
	}

	@Override
	public Optional<Customer> findCustomerById(Integer custId) {
		
		return customerRepo.findById(custId);
	}

}
