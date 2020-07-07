package com.charter.demo.dto;

import lombok.Data;

@Data
public class PurchaseTransactionDTO {
	
	private Integer customerId;
	private String ItemPurchased;
	private Double amount = 0d;

}
