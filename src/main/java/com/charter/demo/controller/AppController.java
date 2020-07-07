package com.charter.demo.controller;

import java.time.Month;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.charter.demo.dto.ApiResponse;
import com.charter.demo.dto.ApiResponse.Status;
import com.charter.demo.dto.PurchaseTransactionDTO;
import com.charter.demo.entity.Customer;
import com.charter.demo.entity.CustomerPurchase;
import com.charter.demo.entity.CustomerTotalRewards;
import com.charter.demo.entity.MonthlyRewards;
import com.charter.demo.exception.ValidationException;
import com.charter.demo.service.CustomerService;
import com.charter.demo.service.PurchaseService;

@RestController
@RequestMapping(path = "/api/v1")
public class AppController {

	@Autowired
	CustomerService customerService;

	@Autowired
	PurchaseService purchaseService;

	@GetMapping
	@RequestMapping(path = "customers")
	public @ResponseBody Object getAllCustomer() {

		return new ApiResponse<List<Customer>>(customerService.fetchAllCustomers());

	}

	@PostMapping
	@RequestMapping(path = "add/customer")
	public @ResponseBody Object addNewCustomer(@RequestBody Customer customer) {

		if (StringUtils.isBlank(customer.getName())) {
			throw new ValidationException("Custome name is blank");
		}

		return new ApiResponse<Customer>("Customer added successfully.", customerService.addNewCustomer(customer));
	}

	@DeleteMapping
	@RequestMapping(path = "remove/customer")
	public @ResponseBody Object removeCustomer(@RequestParam Integer custId) {

		validateIfCustomerPresent(custId);

		customerService.removeCustomer(custId);

		return new ApiResponse(Status.SUCCESS, "Customer removed Successfully");
	}

	@PostMapping
	@RequestMapping(path = "add/purchase")
	public @ResponseBody Object addNewCustomer(@RequestBody PurchaseTransactionDTO customerPurchase) {

		validateIfCustomerPresent(customerPurchase.getCustomerId());

		if (StringUtils.isBlank(customerPurchase.getItemPurchased())) {
			throw new ValidationException("Item purchased details not present.");
		}

		return new ApiResponse<CustomerPurchase>("Purchase transaction added successfully.", purchaseService.addPurchaseTransaction(customerPurchase));
	}

	@GetMapping
	@RequestMapping(path = "customer/purchase/details")
	public @ResponseBody Object getAllPurchaseForCustomer(@RequestParam Integer custId) {

		validateIfCustomerPresent(custId);

		return new ApiResponse<List<CustomerPurchase>>(purchaseService.getAllCustomerPurchase(custId));
	}

	@GetMapping
	@RequestMapping(path = "customer/{month}/purchase/details")
	public @ResponseBody Object getAllPurchaseForCustomer(@RequestParam Integer custId, @PathVariable String month) {

		validateIfCustomerPresent(custId);

		Month validatedMonth;
		try {
			validatedMonth = Month.valueOf( month.toUpperCase());
		} catch (Exception ex) {
			throw new ValidationException("Invalid month [" + month + "]	 passed in the url.");
		}

		return new ApiResponse<List<CustomerPurchase>>(
				purchaseService.getAllCustomerPurchaseForMonth(custId, validatedMonth));
	}

	@DeleteMapping
	@RequestMapping(path = "remove/purchase/transaction")
	public @ResponseBody Object removePurchase(@RequestParam Integer txnId) {
		
		if(txnId == null || !purchaseService.getCustomerPurchasebyId(txnId).isPresent()) {
			throw new ValidationException("Invalid Purchase transaction Id.");
		}
		
		 purchaseService.removePurchaseRecord(txnId);
		
		return new ApiResponse(Status.SUCCESS, "Purchase transaction removed successfully");
	}

	@GetMapping
	@RequestMapping(path = "customer/total/rewards")
	public @ResponseBody Object getCustomerTotalRewards(@RequestParam Integer custId) {
		
		validateIfCustomerPresent(custId);
		
		return new ApiResponse<List<CustomerTotalRewards>>(purchaseService.getCustomerTotalRewards(custId));
	}

	@GetMapping
	@RequestMapping(path = "customer/monthly/rewards")
	public @ResponseBody Object getCustomerMonthlyRewards(@RequestParam Integer custId) {
		
		validateIfCustomerPresent(custId);
		
		return new ApiResponse<List<MonthlyRewards>>(purchaseService.getMonthlyRewards(custId));
	}
	
	@GetMapping
	@RequestMapping(path = "all/purchases")
	public @ResponseBody Object getCustomerMonthlyRewards() {
		return new ApiResponse<List<CustomerPurchase>>(purchaseService.getAllPurchaseTransactions());
	}

	private void validateIfCustomerPresent(Integer custId) {
		if (custId == null || !customerService.findCustomerById(custId).isPresent()) {
			throw new ValidationException("Invalid Customer.");
		}
	}

}
