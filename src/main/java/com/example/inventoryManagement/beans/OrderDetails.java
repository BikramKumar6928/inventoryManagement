package com.example.inventoryManagement.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String id;
	private Supplier supplier;
	private Owner owner;
	private List<ProductCount> products;
	private double totalAmount = 0;
	private long totalProducts = 0;
	private boolean isUpdated = false;


	public OrderDetails(Supplier supplier, Owner owner, List<ProductCount> products) {
		this.supplier = supplier;
		this.owner = owner;
		this.products = products;

		updateMembers();
	}

	private void updateMembers() {
		if(isUpdated){
			return;
		}
		isUpdated = true;
		products.forEach(product ->{
			totalAmount += product.getPrice() * product.getCount();
			totalProducts += product.getCount();
		});
	}


	public void printOrderDetails(){
		System.out.println();
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public Owner getOwner() {
		return owner;
	}

	public void addProducts(ProductCount productCount){
		products.add(productCount);
	}

	public void addProducts(List<ProductCount> productCounts){
		products.addAll(productCounts);
	}

	public List<ProductCount> getProducts() {
		return products;
	}

	public double getTotalAmount() {
		updateMembers();
		return totalAmount;
	}

	public long getTotalProducts() {
		updateMembers();
		return totalProducts;
	}

	public String getId() {
		return id;
	}
}
