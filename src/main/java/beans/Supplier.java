package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Supplier {
	private List<ProductCount> productsAvailable;
	private String id;
	private double bankBalance;

	public void supplyStock(OrderDetails orderDetails){

	}

	public void receivePayment(OrderDetails orderDetails){

	}

	public Supplier(double bankBalance ,List<ProductCount> productsAvailable) {
		this.productsAvailable = productsAvailable;
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
		this.productsAvailable.add(productCount);
	}

	public void updateProductInList(ProductCount productCount){
		productsAvailable = productsAvailable.stream().map(productCount1 -> {
			if(productCount1.getProductId() == productCount.getProductId()){
				return productCount;
			}
			return productCount1;
		}).collect(Collectors.toList());
	}

	public List<ProductCount> getProductsAvailable() {
		return productsAvailable;
	}

	public void setProductsAvailable(List<ProductCount> productsAvailable) {
		this.productsAvailable = productsAvailable;
	}

	public double getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(double bankBalance) {
		this.bankBalance = bankBalance;
	}
}
