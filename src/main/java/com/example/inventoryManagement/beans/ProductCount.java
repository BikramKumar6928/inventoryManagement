package com.example.inventoryManagement.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ProductCount {
	@Id
	private String id;
	@OneToOne
	private Product product;
	private int count;

	public ProductCount(Product product, int count) {

		this.product = product;
		this.count = count;
	}

	public double getPrice(){
		return product.getPrice();
	}

	public int getCount(){
		return count;
	}

	public String getProductId(){
		return this.product.getId();
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
}
