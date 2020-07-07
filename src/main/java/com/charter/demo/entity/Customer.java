package com.charter.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CustomerSeq")
	@SequenceGenerator(initialValue = 4, name="CustomerSeq")
	private Integer id;
	
	private String name;
	
	public Customer() {
	}
	
	public Customer(Integer id) {
		this.id = id;
	}

}
