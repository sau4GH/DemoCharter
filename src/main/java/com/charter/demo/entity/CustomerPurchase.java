package com.charter.demo.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "customerPurchase")
public class CustomerPurchase {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CustomerPurchaseSeq")
	@SequenceGenerator(initialValue = 11, name="CustomerPurchaseSeq")
	private Integer id;
	
	private String item;
	
	private Double itemAmount;
	
	private Long rewards;
	
	@OneToOne
	@JoinColumn(name = "custId", referencedColumnName = "id")
	private Customer customer;
	
	private Date txnDate;
	
	

}
