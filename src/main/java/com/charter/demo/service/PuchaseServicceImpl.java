package com.charter.demo.service;

import java.sql.Date;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charter.demo.dao.CustomerPurchaseRepo;
import com.charter.demo.dto.PurchaseTransactionDTO;
import com.charter.demo.entity.Customer;
import com.charter.demo.entity.CustomerPurchase;
import com.charter.demo.entity.CustomerTotalRewards;
import com.charter.demo.entity.MonthlyRewards;

@Service
public class PuchaseServicceImpl implements PurchaseService {

	@Autowired
	private CustomerPurchaseRepo customerPurchaseRepo;
	
	private static Long DOLLER_50_THRESHOLD = 50l;
	private static Long DOLLER_100_THRESHOLD = 100l;

	@Override
	public CustomerPurchase addPurchaseTransaction(PurchaseTransactionDTO customerPurchaseTrans) {
		
		CustomerPurchase purchase = new CustomerPurchase();

		purchase.setCustomer(new Customer(customerPurchaseTrans.getCustomerId()));
		purchase.setItem(customerPurchaseTrans.getItemPurchased());
		purchase.setItemAmount(customerPurchaseTrans.getAmount());
		purchase.setRewards(calculateRewards(customerPurchaseTrans.getAmount()));
		purchase.setTxnDate(new Date(System.currentTimeMillis()));

		return customerPurchaseRepo.save(purchase);
	}

	@Override
	public Optional<CustomerPurchase> getCustomerPurchasebyId(Integer txnId) {
		
		return customerPurchaseRepo.findById(txnId);
	}
	
	@Override
	public List<CustomerPurchase> getAllCustomerPurchase(Integer custId) {
		return sortBasedOnDate(customerPurchaseRepo.findByCustId(custId));

	}

	@Override
	public List<CustomerPurchase> getAllCustomerPurchaseForMonth(Integer custId, Month month) {
		return sortBasedOnDate(customerPurchaseRepo.findByMonth(custId, month.name()));
	}

	@Override
	public void removePurchaseRecord(Integer id) {
		customerPurchaseRepo.deleteById(id);

	}
	
	private Long calculateRewards(Double purchaseAmount) {
		
		Long roundFigure = (purchaseAmount == null ? 0l : Math.round(purchaseAmount));
		Long rewardsForTxn = 0l;
		
		if (roundFigure.compareTo(DOLLER_100_THRESHOLD) > 0) {
			
			rewardsForTxn = 50l + (roundFigure - DOLLER_100_THRESHOLD) * 2;
			
		} else if ((roundFigure.compareTo(DOLLER_50_THRESHOLD) > 0 && roundFigure.compareTo(DOLLER_100_THRESHOLD) <= 0)) {
			
			rewardsForTxn = roundFigure - DOLLER_50_THRESHOLD;
			
		} 
		
		return rewardsForTxn;
	}

	@Override
	public List<CustomerTotalRewards> getCustomerTotalRewards(Integer custId) {
		
		return customerPurchaseRepo.getCustomerTotalRewards(custId);
		
	}

	@Override
	public List<MonthlyRewards> getMonthlyRewards(Integer custId) {
		
		List<MonthlyRewards> results = customerPurchaseRepo.getMonthlyRewards(custId);
		
		// Sort based on Month.
		results = results.stream().sorted((ob1, ob2) -> {
		
			Month month1 = Month.valueOf(ob1.getMonth().toUpperCase());
			Month month2 = Month.valueOf(ob2.getMonth().toUpperCase());
			
			return month1.getValue() - month2.getValue();
		
		}).collect(Collectors.toList());
		
		return results;
		
	}

	@Override
	public List<CustomerPurchase> getAllPurchaseTransactions() {
		
		return sortBasedOnDate(customerPurchaseRepo.findAll());
	}
	
	
	private List<CustomerPurchase> sortBasedOnDate(List<CustomerPurchase> result) {

		return result.stream().sorted((ob1, ob2) -> ob2.getTxnDate().compareTo(ob1.getTxnDate()))
				.collect(Collectors.toList());

	}

}
