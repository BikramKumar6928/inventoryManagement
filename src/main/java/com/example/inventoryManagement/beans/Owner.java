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
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String id;
	private String name;
	private double bankBalance;
	private List<ProductCount> stocks;
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

	public void addStocks(Product product, int number){
		addStocks(new ProductCount(product,number));
	}

	public void addStocks(ProductCount stockAmount){
		List<ProductCount> productCounts = new ArrayList<>();
		productCounts.add(stockAmount);
		addStocks(productCounts);
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

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getId() {
		return id;
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

	public void incrementBankBalance(double totalAmount) {
		setBankBalance(getBankBalance() + totalAmount);
	}
}