package com.charter.demo.service;

import java.time.Month;
import java.util.List;
import java.util.Optional;

import com.charter.demo.dto.PurchaseTransactionDTO;
import com.charter.demo.entity.CustomerPurchase;
import com.charter.demo.entity.CustomerTotalRewards;
import com.charter.demo.entity.MonthlyRewards;

public interface PurchaseService {
	
	public CustomerPurchase addPurchaseTransaction(PurchaseTransactionDTO customerPurchaseTrans);
	public Optional<CustomerPurchase> getCustomerPurchasebyId(Integer txnId);
	public List<CustomerPurchase> getAllPurchaseTransactions();
	
	public List<CustomerPurchase> getAllCustomerPurchase(Integer custId);
	public List<CustomerPurchase> getAllCustomerPurchaseForMonth(Integer custId, Month month);
	public void removePurchaseRecord(Integer id);
	
	public List<CustomerTotalRewards> getCustomerTotalRewards(Integer custId) ;
	public List<MonthlyRewards> getMonthlyRewards(Integer custId) ;
	

}
