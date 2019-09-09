package beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Owner {
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
		this.stocks.add(stockAmount);
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public void sellProducts(List<ProductCount> productCount) {
		boolean isPossible = true;
		for(int i=0;i<productCount.size();i++){
			ProductCount item = productCount.get(i);
			stocks.stream().forEach(productCount1 -> {
				if(item.getProductId() == productCount1.getProductId() && item.getCount() > productCount1.getCount()){
//					isPossible = false;
				}
			});
		}
	}
}