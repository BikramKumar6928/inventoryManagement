package com.example.inventoryManagement.beans;

import com.example.inventoryManagement.exceptions.LesserItemsInInventory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String id;
	private List<ProductCount> stocks;
	private double bankBalance;


	public Supplier(double bankBalance ,List<ProductCount> stocks) {
		this.stocks = stocks;
		this.bankBalance = bankBalance;
	}

	public Supplier(double bankBalance) {
		this(bankBalance,new ArrayList<>());
	}

	public Supplier() {
		this(0);
	}

	public String getId() {
		return id;
	}

	public void addProductToList(ProductCount productCount){
		this.stocks.add(productCount);
	}

	public void updateProductInList(ProductCount productCount){
		stocks = stocks.stream().map(productCount1 -> {
			if(productCount1.getProductId() == productCount.getProductId()){
				return productCount;
			}
			return productCount1;
		}).collect(Collectors.toList());
	}

	public List<ProductCount> getStocks() {
		return stocks;
	}

	public void setStocks(List<ProductCount> stocks) {
		this.stocks = stocks;
	}

	public double getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(double bankBalance) {
		this.bankBalance = bankBalance;
	}

	public void incrementBankBalance(double totalAmount) {
		setBankBalance(getBankBalance() + totalAmount);
	}

	public void addStocks(List<ProductCount> itemList){
		List<ProductCount> updatedStocks =
				stocks.stream().
						map(productCount1 -> {
							int originalCount =  getCountWithProductId(productCount1.getProductId());
							productCount1.setCount(originalCount + productCount1.getCount());
							return productCount1;
						})
						.collect(Collectors.toList());
		setStocks(updatedStocks);
	}

	public void removeStocks(List<ProductCount> itemList){
		checkItemsAvailable(itemList);
		List<ProductCount> updatedStocks =
				stocks.stream().
						map(productCount1 -> {
							int originalCount =  getCountWithProductId(productCount1.getProductId());
							productCount1.setCount(originalCount - productCount1.getCount());
							return productCount1;
						})
						.collect(Collectors.toList());
		setStocks(updatedStocks);
	}

	private void checkItemsAvailable(List<ProductCount> productCount) {
		boolean areItemsAvailable = productCount.stream().filter(productCount1 -> {
			ProductCount stock = getStockWithId(productCount1.getProductId());
			return (
					stock != null &&
							stock.getCount() > productCount1.getCount()
			);
		}).findAny().orElse(null) == null;

		if (!areItemsAvailable){
			throw new LesserItemsInInventory();
		}
	}

	public ProductCount getStockWithId(String productId) {
		return stocks.stream()
				.filter(stock ->{
					return stock.getProductId() == productId;
				})
				.findAny()
				.orElse(null);

	}

	public int getCountWithProductId(String productId) {
		ProductCount productCount = getStockWithId(productId);
		if(productCount == null){
			return 0;
		}
		return productCount.getCount();
	}

	public void decrementBankBalance(double totalAmount) {
		setBankBalance(getBankBalance() - totalAmount);
	}
}
