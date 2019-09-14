package com.example.inventoryManagement.beans;

import com.example.inventoryManagement.enums.TrackStatus;
import com.example.inventoryManagement.exceptions.LesserItemsInInventory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String id;
	private String name;
	private double bankBalance;
	@OneToMany(cascade=CascadeType.ALL, targetEntity=ProductCount.class)
	private List<ProductCount> stocks;
	@OneToOne
	private Manager manager;




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(double bankBalance) {
		this.bankBalance = bankBalance;
	}

	public List<ProductCount> getStocks() {
		return stocks;
	}

	public void setStocks(List<ProductCount> stocks) {
		this.stocks = stocks;
	}


	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getId() {
		return id;
	}


	public void decrementBankBalance(double totalAmount) {
		setBankBalance(getBankBalance() - totalAmount);
	}

	public void incrementBankBalance(double totalAmount) {
		setBankBalance(getBankBalance() + totalAmount);
	}


}